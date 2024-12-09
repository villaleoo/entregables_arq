package org.example.microgestion.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.EntityNotFoundException;
import org.example.microgestion.DTO.externals.account.AccountDTO;
import org.example.microgestion.DTO.pauses.ContinueTravelDTO;
import org.example.microgestion.DTO.pauses.PauseDTO;
import org.example.microgestion.DTO.payments.PaymentDTO;
import org.example.microgestion.DTO.externals.monopatin.UpdateMonoDTO;
import org.example.microgestion.DTO.travels.*;
import org.example.microgestion.DTO.externals.account.UsuarioDTO;
import org.example.microgestion.DTO.externals.monopatin.MonopatinDTO;
import org.example.microgestion.DTO.externals.monopatin.KmsOnMonoTravelingDTO;
import org.example.microgestion.DTO.externals.monopatin.ReportTravelsMonoDTO;
import org.example.microgestion.DTO.externals.stops.StopDTO;
import org.example.microgestion.DTO.pauses.PauseResponseDTO;
import org.example.microgestion.DTO.payments.ResponseDebitDTO;
import org.example.microgestion.DTO.reports.ReportMonopatinesDTO;
import org.example.microgestion.DTO.reports.ReportTotalBilledDTO;
import org.example.microgestion.exceptions.NotFoundEntityException;
import org.example.microgestion.feingClient.*;
import org.example.microgestion.model.PausasViaje;
import org.example.microgestion.model.Tarifa;
import org.example.microgestion.model.Viaje;
import org.example.microgestion.repository.FeeRepository;
import org.example.microgestion.repository.PauseTravelRepository;
import org.example.microgestion.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class TravelService {

    @Autowired
    TravelRepository repository;
    @Autowired
    FeeRepository feesRepository;
    @Autowired
    PauseTravelRepository pauseRepository;

    @Autowired
    StopClient stopClient;
    @Autowired
    MonoClient monoClient;
    @Autowired
    UserClient userClient;
    @Autowired
    AuthClient accountClient;




    public CreateTravelDTO initTravel(InitTravelDTO travel){
        StopDTO stopEnd=this.getStop(travel.getId_stop_end());
        MonopatinDTO mono= this.getMono(travel.getId_monopatin());
        StopDTO stopInit=this.getStop(mono.getStop_assign().getId_stop());
        UsuarioDTO user = this.getUser(travel.getId_user());
        AccountDTO account = this.getAccount(travel.getId_account());
        PaymentDTO mp = this.getPayment(account.getId_account());

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
        Pageable pageable = PageRequest.of(0, 1);
        List<Tarifa> fees = feesRepository.findLatestTarifa(Date.from(zonedDateTime.toInstant()), pageable);
        Tarifa fee = fees.get(0);



        if(mp.getBalance() <= 0){
            throw new RuntimeException("Debe tener saldo ingresado en su cuenta de mercado pago para hacer un viaje.");
        }
        if(fees.size() == 0){
            throw new RuntimeException("No hay tarifa de servicio valida.");
        }

        if(Objects.equals(mono.getStop_assign().getId_stop(), stopEnd.getId_stop())){
            throw new RuntimeException("La parada de inicio no puede ser la misma que la de final.");
        }

        if(!account.isAvailable()){
            throw new RuntimeException("La cuenta ingresada no esta habilitada para su uso.");
        }
        if(!mono.isAvailable()){
            throw new RuntimeException("El monopatin ingresado se encuentra en mantenimiento.");
        }




        Viaje newTravel=new Viaje(user.getId_user(), account.getId_account(), mono.getId_monopatin(),
                    stopInit.getId_stop(),stopEnd.getId_stop(), Date.from(zonedDateTime.toInstant()),fee.getNormalFee());

        newTravel.setFee(fee);
        newTravel.setKmsMonoInit(mono.getKms());
        newTravel.setPauses(new ArrayList<>());

        this.repository.save(newTravel);
        double totalPay= newTravel.getTime() * newTravel.getFee().getNormalFee();

        ResponseDebitDTO debit = this.debitMoneyOfAccount(travel.getId_account(),totalPay,"Debito automatico por inicio de viaje: $"+totalPay);

        return new CreateTravelDTO(newTravel.getId_travel(), newTravel.getDate_init(),debit);

    }

    public CloseTravelDTO endTravel(Long id){
        Optional<Viaje> res =this.repository.findById(id);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
        if(res.isEmpty()){
            throw new NotFoundEntityException("No se pudo encontrar viaje con id: "+id);
        }

        Viaje travel= res.get();
        if(travel.isPause()){
            throw new RuntimeException("Para finalizar el viaje primero debe finalizar la pausa activa.");
        }
        if(!travel.isState()){
            throw new RuntimeException("El viaje indicado ya finalizo.");
        }


        StopDTO endStop =this.getStop(travel.getId_stop_end());
        MonopatinDTO mono = this.getMono(travel.getId_monopatin());

        if(!mono.getLocation().equals(endStop.getLocation())){
            throw new RuntimeException("El monopatin con id: "+mono.getId_monopatin()+" no esta ubicado en la parada final, debe mover el monopatin a la ubicacion: x="+endStop.getLocation().getX()+" y="+endStop.getLocation().getY());
        }

        this.monoClient.update(travel.getId_monopatin(),new UpdateMonoDTO(true,endStop));

        travel.setDate_end(Date.from(zonedDateTime.toInstant()));
        travel.setState(false);

        long totalMinutesTravel;
        double totalPay;    /*incluye lo que se le cobro al inicio*/
        String message;

        if(travel.getPauses().isEmpty()){
            totalMinutesTravel= Math.abs(this.getMinutesDifference(travel.getDate_end(),travel.getDate_init()));
            totalPay=totalMinutesTravel*travel.getFeeActive();
            message="Debito automatico por finalizacion de viaje. Total cobrado $"+(totalPay+travel.getFee().getNormalFee())+" tiempo de viaje: "+totalMinutesTravel+"min.";

        }else{
            PausasViaje ultimatePause =this.getUltimatePause(travel.getPauses());

            totalMinutesTravel=Math.abs(this.getMinutesDifference(Date.from(zonedDateTime.toInstant()),ultimatePause.getDate_end_pause()));
            totalPay=totalMinutesTravel * travel.getFeeActive();
            message="Debito automatico por finalizacion de viaje. Total cobrado $"+(totalPay+travel.getFee().getNormalFee())+" correspondiente a los minutos entre la finalizacion de la ultima pausa y la fecha de fin de viaje ("+totalMinutesTravel+"min.)";
        }


        travel.setTime((int) this.getMinutesDifference(travel.getDate_init(),travel.getDate_end()));
        travel.setTotal_pay(travel.getTotal_pay()+ totalPay);
        travel.setKmsTraveled(mono.getKms()-travel.getKmsMonoInit());       /*los km recorridos en el viaje es la diferencia de los km del monopatin al inicio-los que tiene al final del viaje*/

        this.repository.save(travel);

        ResponseDebitDTO debit = this.debitMoneyOfAccount(travel.getId_account(),totalPay,message);

        return new CloseTravelDTO(travel.getId_travel(),travel.getTotal_pay(),travel.getKmsTraveled() ,travel.getDate_end(),debit);

    }

    public PauseResponseDTO pauseTravel(Long id){
        Optional<Viaje> res =this.repository.findById(id);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
        if(res.isEmpty()){
            throw new NotFoundEntityException("No se pudo encontrar viaje con id: "+id);
        }
        if(res.get().isPause()){
            throw new RuntimeException("El viaje ya esta en pausa.");
        }
        if(!res.get().isState()){
            throw new RuntimeException("El viaje indicado ya finalizo.");
        }


        Viaje travel= res.get();
        PausasViaje pause =new PausasViaje();
        pause.setDate_init_pause(Date.from(zonedDateTime.toInstant()));
        pause.setTravel(travel);
        pause.setDate_end_pause(null);
        pause.setMinPause(0);

        this.pauseRepository.save(pause);

        travel.setPause(true);

        List<PausasViaje> pauses=travel.getPauses();

        pauses.add(pause);
        travel.setPauses(pauses);

        this.repository.save(travel);

        return new PauseResponseDTO(id, Date.from(zonedDateTime.toInstant()));

    }

    public ContinueTravelDTO unPauseTravel(Long id){
        int MAX_MINUTES=15;
        Optional<Viaje> res =this.repository.findById(id);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));

        if(res.isEmpty()){
            throw new NotFoundEntityException("No se pudo encontrar viaje con id: "+id);
        }
        if(!res.get().isPause()){
            throw new RuntimeException("El viaje no esta en pausa.");
        }

        if(!res.get().isState()){
            throw new RuntimeException("El viaje indicado ya finalizo.");
        }


        Viaje travel= res.get();
        List<PausasViaje> pauses = travel.getPauses();


        if(pauses.isEmpty()){
            throw new RuntimeException("Fallo al encontrar la pausa.");
        }

        PausasViaje pause = this.getPauseDateEndInNull(pauses);

        long pauseMinutes = Math.abs(this.getMinutesDifference(pause.getDate_init_pause(),Date.from(zonedDateTime.toInstant())));

        double totalMinutes=Math.abs(this.getMinutesDifference(travel.getDate_init(),Date.from(zonedDateTime.toInstant())));
        double totalPay =totalMinutes*travel.getFeeActive();
        String message= "Debito automatico por reanudacion de viaje. Cobrado $"+totalPay+" correspondientes a los minutos entre el comienzo del viaje y el final de ésta pausa. ("+totalMinutes+" minutos).";

        /*si ya hizo pausa anteriormente, solamente le cobro desde el fin de la ultima pausa hasta el fin de esta pausa*/
        /*si NO tiene pausas le cobro desde el inicio del viaje hasta el fin de esta pausa*/
        if(travel.getQuantityEndPauses() > 0){
            PausasViaje p = this.getUltimatePause(travel.getPauses());

            totalMinutes=Math.abs(this.getMinutesDifference(p.getDate_end_pause(),Date.from(zonedDateTime.toInstant())));
            totalPay=totalMinutes*travel.getFeeActive();
            message="Debito automatico por reanudacion de viaje. Cobrado $"+totalPay+" correspondientes a los minutos entre la ultima pausa y el final de ésta. ("+totalMinutes+" minutos)";

        }


        if(pauseMinutes > MAX_MINUTES){
            travel.setFeeActive(travel.getFee().getAdditionalFee());
        }

        pause.setDate_end_pause(Date.from(zonedDateTime.toInstant()));
        pause.setMinPause((int) pauseMinutes);


        this.pauseRepository.save(pause);


        travel.setPause(false);
        travel.setQuantityEndPauses(travel.getQuantityEndPauses()+1);
        travel.setTotal_pay(travel.getTotal_pay()+totalPay);

        this.repository.save(travel);

        ResponseDebitDTO debit = this.debitMoneyOfAccount(travel.getId_account(),totalPay,message);


        return new ContinueTravelDTO((long)totalMinutes,pauseMinutes,debit);

    }


    ///////////////////////////////////////////*METODOS PARA REPORTES*//////////////////////////////////////////////////////////////////////////////////
    public List<ReportMonopatinesDTO> findListMonopatines(Integer year, Integer minTrip){

        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = startOfYear.plusYears(1).minusDays(1);

        Date startDate = Date.from(startOfYear.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfYear.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

        Optional<List<ReportMonopatinesDTO>> res = this.repository.findAllIdMonopatinByFecha(startDate,endDate,minTrip);

        if(res.isPresent()){
            return res.get();
        }

        throw new NotFoundEntityException("No se pudo encontrar la lista de monopatines.");
    }

    public ReportTotalBilledDTO findTotalBilled (Integer monthInit, Integer monthEnd, Integer year){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 =Calendar.getInstance();
        Integer auxYear=year;
        Integer auxMonth=monthEnd;
        if(year == null){
            year= LocalDate.now().getYear();
            auxYear=LocalDate.now().getYear();
        }
        if(monthEnd == 12){
            auxMonth=0;
            auxYear=auxYear+1;
        }
        c1.set(year,monthInit-1,1,0,0,0);
        c1.set(Calendar.MILLISECOND,0);
        c2.set(auxYear,auxMonth,1,0,0,0);
        Date dateInit=c1.getTime();
        Date dateEnd=c2.getTime();
        Optional<Long> res =this.repository.findTotalBilled(dateInit,dateEnd);

        if(res.isPresent()){
            Long total = res.get();
            return new ReportTotalBilledDTO(monthInit,monthEnd,year,total);
        }

        throw new NotFoundEntityException("No se pudo generar el reporte de facturacion entre rango de meses.");
    }

    public List<TravelDTO> findAll(){

        return this.repository.findAllProtected();
    }

    public IdTravelDTO findTravelIdActiveOfMono(String idMono){
        Optional<Viaje> res = this.repository.findTravelActiveOfMono(idMono);

        if(res.isPresent()){
            Viaje travel = res.get();
            return new IdTravelDTO(travel.getId_travel());
        }

        return null;
    }

    public ReportTravelsMonoDTO findTravelsDetailsOfMono(String idMono){
        Optional<ReportTravelsMonoDTO> res = this.repository.findDetailsTravelsOfMonopatin(idMono);

        if(res.isPresent()){

            return res.get();
        }

        throw new EntityNotFoundException("No hay monopatines con id: "+idMono);
    }


    private ResponseDebitDTO debitMoneyOfAccount(Long idAccountAsociate, double total,String message){
        ResponseEntity<?> res = this.userClient.debitCreditForTravel(idAccountAsociate, new ResponseDebitDTO(message,total));

        if(res.getStatusCode().is2xxSuccessful() && res.getBody()!=null){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.convertValue(res.getBody(), ResponseDebitDTO.class);
        }

        throw new NotFoundEntityException("No se pudo realizar el cobro a la cuenta: "+idAccountAsociate);
    }




    ///////////////////////////////////////////*METODOS PARA BUSQUEDAS DE REGISTROS*///////////////////////////////////////////////////////////////////////////

    private PausasViaje getUltimatePause(List<PausasViaje> list){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));

        long minDiff=99999999;
        PausasViaje pauseSelec=null;

        for(PausasViaje p : list){
            long currentDiff= Math.abs(this.getMinutesDifference(p.getDate_end_pause(),Date.from(zonedDateTime.toInstant())));
            if(currentDiff < minDiff){
                minDiff=currentDiff;
                pauseSelec=p;
            }
        }

        return pauseSelec;
    }

    private long getMinutesDifference(Date start, Date end) {
        long diffInMillies = end.getTime() - start.getTime();
        return diffInMillies / (60 * 1000);
    }


    private PausasViaje getPauseDateEndInNull(List<PausasViaje> list){
        for(PausasViaje p : list){
            if(p.getDate_end_pause() == null){
                return p;
            }
        }

        return null;
    }


    private UsuarioDTO getUser(Long id){
        if(id == null){
            throw new RuntimeException("El id de cuenta no debe ser null.");
        }

        ResponseEntity<?> resUser= this.userClient.getById(id);

        if(resUser.getStatusCode().is2xxSuccessful() && resUser.getBody()!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.convertValue(resUser.getBody(), ObjectNode.class);
            node.remove("role");

            return objectMapper.convertValue(node, UsuarioDTO.class);
        }

        throw new NotFoundEntityException("No se pudo encontrar cuenta con id: "+id);
    }


    private MonopatinDTO getMono(String id){
        ResponseEntity<?> resMono= this.monoClient.getById(id);

        if(resMono.getStatusCode().is2xxSuccessful() && resMono.getBody()!=null){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.convertValue(resMono.getBody(), MonopatinDTO.class);
        }

        throw new NotFoundEntityException("No se pudo encontrar monopatin con id: "+id);
    }

    private StopDTO getStop(Long id){
        ResponseEntity<?> resStop= this.stopClient.getById(id);

        if(resStop.getStatusCode().is2xxSuccessful() && resStop.getBody() !=null){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.convertValue(resStop.getBody(), StopDTO.class);
        }

        throw new NotFoundEntityException("No se pudo encontrar parada con id: "+id);
    }

    private AccountDTO getAccount(Long id) {
        ResponseEntity<?> resAcc=this.accountClient.getById(id);

        if(resAcc.getStatusCode().is2xxSuccessful() && resAcc.getBody() != null){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.convertValue(resAcc.getBody(), AccountDTO.class);

        }
        throw new NotFoundEntityException("No se pudo encontrar cuenta con id: "+id);
    }

    private PaymentDTO getPayment(Long id_account){
        ResponseEntity<?> resPay=this.userClient.getByAccountAsociate(id_account);

        if(resPay.getStatusCode().is2xxSuccessful() && resPay.getBody() != null){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.convertValue(resPay.getBody(), PaymentDTO.class);
        }
        throw new NotFoundEntityException("No se pudo encontrar medio de pago asociado a la cuenta con id: "+id_account);
    }
}
