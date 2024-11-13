package org.example.microgestion.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.EntityNotFoundException;
import org.example.microgestion.DTO.externals.monopatin.UpdateMonoDTO;
import org.example.microgestion.DTO.pauses.PauseDTO;
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
import org.example.microgestion.feingClient.MonoClient;
import org.example.microgestion.feingClient.StopClient;
import org.example.microgestion.feingClient.UserClient;
import org.example.microgestion.model.PausasViaje;
import org.example.microgestion.model.Tarifa;
import org.example.microgestion.model.Viaje;
import org.example.microgestion.repository.FeeRepository;
import org.example.microgestion.repository.PauseTravelRepository;
import org.example.microgestion.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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



    public CreateTravelDTO initTravel(InitTravelDTO travel){
        StopDTO stopEnd=this.getStop(travel.getId_stop_end());
        MonopatinDTO mono= this.getMono(travel.getId_monopatin());
        StopDTO stopInit=this.getStop(mono.getStop_assign().getId_stop());
        UsuarioDTO user = this.getUser(travel.getId_user());
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
        boolean isActiveAccount = user.getAccount().isAvailable();
        Optional<Tarifa> res =this.feesRepository.findTopByDateBefore(Date.from(zonedDateTime.toInstant()));


        if(isActiveAccount && res.isPresent() && !Objects.equals(mono.getStop_assign().getId_stop(), stopEnd.getId_stop())){
            Tarifa fee = res.get();

            Viaje newTravel=new Viaje(user.getId_user(),user.getAccount().getId_account(), mono.getId_monopatin(),
                    stopInit.getId_stop(),stopEnd.getId_stop(), Date.from(zonedDateTime.toInstant()),null,true,0,false,fee,0);

            newTravel.setFee(fee);
            this.repository.save(newTravel);

            return new CreateTravelDTO(newTravel.getId_travel(), newTravel.getDate_init());
        }

        if(Objects.equals(mono.getStop_assign().getId_stop(), stopEnd.getId_stop())){
            throw new RuntimeException("La parada donde esta el monopatin y la de destino son las mismas.");
        }

        if(res.isEmpty()){
            throw new RuntimeException("No hay tarifa válida.");
        }

        throw new EntityNotFoundException("La cuenta con id: "+user.getAccount().getId_account()+" no esta habilitada.");

    }

    public List<ReportMonopatinesDTO> findListMonopatines(Integer year, Integer minTrip){

        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = startOfYear.plusYears(1).minusDays(1);

        Date startDate = Date.from(startOfYear.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfYear.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

        Optional<List<ReportMonopatinesDTO>> res = this.repository.findAllIdMonopatinByFecha(startDate,endDate,minTrip);

        if(res.isPresent()){
            List<ReportMonopatinesDTO> list = res.get();
            return list;
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

    public CloseTravelDTO endTravel(Long id){
        Optional<Viaje> res =this.repository.findById(id);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
        if(res.isPresent()){
            Viaje travel= res.get();
            StopDTO p =this.getStop(travel.getId_stop_end());

            this.monoClient.update(travel.getId_monopatin(),new UpdateMonoDTO(true,p));

            travel.setDate_end(Date.from(zonedDateTime.toInstant()));
            travel.setState(false);

            this.repository.save(travel);

            return new CloseTravelDTO(travel.getId_travel(),travel.getTotal_pay(),travel.getKms() ,travel.getDate_end());
        }

        throw new NotFoundEntityException("No se pudo encontrar viaje con id: "+id);
    }

    public PauseResponseDTO pauseTravel(Long id){
        Optional<Viaje> res =this.repository.findById(id);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));

        if(res.isPresent() && !res.get().isPause()){
            Viaje travel= res.get();
            PausasViaje pause =new PausasViaje();
            pause.setDate_init_pause(Date.from(zonedDateTime.toInstant()));
            pause.setTravel(travel);
            pause.setDate_end_pause(null);
            pause.setMinPause(0);

            this.pauseRepository.save(pause);

            travel.setPause(true);

            this.repository.save(travel);

            return new PauseResponseDTO(id, Date.from(zonedDateTime.toInstant()));
        }
        if(res.get().isPause()){
            throw new RuntimeException("El viaje ya esta en pausa.");
        }

        throw new NotFoundEntityException("No se pudo encontrar viaje con id: "+id);
    }

    public ResponseDebitDTO unPauseTravel(Long id){
        Optional<Viaje> res =this.repository.findById(id);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));


        if(res.isPresent() && res.get().isPause() ){
            Viaje travel= res.get();
            PausasViaje pause = this.findFirstNull(travel.getPauses());
            UsuarioDTO user = this.getUser(travel.getId_user());
            Integer pauseMinutes = this.getMinutesPause(pause.getDate_init_pause(),Date.from(zonedDateTime.toInstant()));

            pause.setDate_end_pause(Date.from(zonedDateTime.toInstant()));
            pause.setMinPause(pauseMinutes);

            this.pauseRepository.save(pause);


            double addFee= travel.getFee().getAdditionalFee();
            double totalPay=pause.getMinPause() * addFee;

            travel.setPause(false);
            travel.setTotal_pay(travel.getTotal_pay()+totalPay);

            this.repository.save(travel);

            return this.debitMoneyOfAccount(user.getAccount().getId_account(),totalPay,"Debito por pausa en monopatin de "+pauseMinutes+" minutos.");

        }

        if(!res.get().isPause()){
            throw new RuntimeException("El viaje no esta en pausa.");
        }

        throw new NotFoundEntityException("No se pudo encontrar viaje con id: "+id);

    }

    private PausasViaje findFirstNull(List<PausasViaje> pauses){

        for(int i =0; i<pauses.size();i++){
            PausasViaje p1=pauses.get(i);

            if(p1.getDate_end_pause() == null){
                return p1;
            }

        }

        return null;
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


    public ResponseDebitDTO updateKmsAndDebitMoney(Long id, KmsOnMonoTravelingDTO distanceTraveled){
        Optional<Viaje> res = this.repository.findById(id);

        if(res.isPresent()){
            Viaje v = res.get();
            double kmsDistance =distanceTraveled.getDistanceTraveledInKms();
            double total= kmsDistance*v.getFee().getNormalFee();

            v.setTotal_pay(v.getTotal_pay()+total);
            v.setKms(v.getKms()+kmsDistance);

            this.repository.save(v);

            return this.debitMoneyOfAccount(v.getId_account(),total,"Debito por uso de monopatin: "+kmsDistance+"kms.");

        }

        throw new NotFoundEntityException("No se encontro viaje al actualizar monto de pago.");
    }


    private ResponseDebitDTO debitMoneyOfAccount(Long idAccount, double total,String message){
        ResponseEntity<?> res = this.userClient.debitCreditForTravel(idAccount, new ResponseDebitDTO(message,total));

        if(res.getStatusCode().is2xxSuccessful() && res.getBody()!=null){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.convertValue(res.getBody(), ResponseDebitDTO.class);
        }

        throw new NotFoundEntityException("No se pudo realizar el cobro a la cuenta: "+idAccount);
    }

    private Integer getMinutesPause(Date initPause,Date endPause){
        long differenceInMillis = endPause.getTime() - initPause.getTime();
        long differenceInMinutes = differenceInMillis / (1000 * 60);

        return (int) differenceInMinutes;
    }



    private UsuarioDTO getUser(Long id){
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
}
