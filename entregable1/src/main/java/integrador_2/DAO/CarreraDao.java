package integrador_2.DAO;

import integrador_2.DTO.CarreraDto;
import integrador_2.DTO.EstudianteDto;
import integrador_2.entidades.Carrera;
import integrador_2.entidades.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CarreraDao {
    private String proyecto;
    public CarreraDao(String pr) {
        this.proyecto = pr;
    }


    public void insertarCarrera(Carrera carrera) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            em.persist(carrera);

            em.getTransaction().commit();
            em.close();
            emf.close();
            System.out.println("Se inserto una carrera exitosamente");
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
    }


    public void borrarCarrera(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Carrera carrera = em.find(Carrera.class, id);
            em.remove(carrera);

            em.getTransaction().commit();
            em.close();
            emf.close();
            System.out.println("Se borró un estudiante exitosamente");
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
    }


    public CarreraDto traerCarrera(int id) {
        CarreraDto carrera = null;
        Carrera aux = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            aux = em.find(Carrera.class, id);
            carrera = new CarreraDto(aux.getId(), aux.getNombre(), aux.getAnios_duracion(), 0);

            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
        return carrera;
    }


    public List traerCarreras() {
        return List.of();
    }

    public List<CarreraDto> traerCarrerasConEstudiantes() {
        List<Carrera> aux = null;
        List<Long> cantidades = null;
        List<CarreraDto> carreras = new ArrayList<>();
        String queryCarreras = "select c from Carrera c\n" +
                "join Inscripcion i \n" +
                "on c.id=i.carrera.id\n" +
                "group by c.id\n" +
                "order by count(*) desc";

        String queryCantidad = "select count(*) from Carrera c\n" +
                "join Inscripcion i \n" +
                "on c.id=i.carrera.id\n" +
                "group by c.id\n" +
                "order by count(*) desc";
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            aux = em.createQuery(queryCarreras, Carrera.class).getResultList();
            cantidades = em.createQuery(queryCantidad, Long.class).getResultList();
            int i = 0;
            for(Carrera c : aux) {
                CarreraDto carr = new CarreraDto(c.getId(), c.getNombre(), c.getAnios_duracion(),Math.toIntExact(cantidades.get(i)));
                carreras.add(carr);
                i++;
            }
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
        return carreras;
    }

    public List<CarreraDto> generarReporte() {
        List<Carrera> listaCarreras = null;
        List<Object[]> respuesta = null;
        List<CarreraDto> carreras = new ArrayList<>();
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            listaCarreras = em.createQuery("select c from Carrera c order by c.nombre", Carrera.class).getResultList();
            String query = "select e, (i.anio_Graduacion+i.anio_inicio)\n" +
                    "from Inscripcion i\n" +
                    "join Estudiante e on i.estudiante.id=e.id\n" +
                    "where i.carrera.id=:carrera and i.anio_Graduacion != 0\n" +
                    "order by (i.anio_Graduacion+i.anio_inicio)";
            for(Carrera carr : listaCarreras) {
                CarreraDto ca = new CarreraDto(carr.getId(), carr.getNombre(), carr.getAnios_duracion());

                respuesta = em.createQuery(query, Object[].class).setParameter("carrera", carr.getId())
                        .getResultList();
                List<EstudianteDto> estudiantes = new ArrayList<>();

                for(Object[] rsp : respuesta) {
                    Estudiante e = (Estudiante) rsp[0];
                    Integer gr = (Integer) rsp[1];
                    EstudianteDto est = new EstudianteDto(e.getNombre(), e.getApellido(),
                            e.getGenero(), e.getCiudad_residencia(), e.getDni(), e.getNro_libreta(), gr);
                    estudiantes.add(est);
                }

                ca.setEstudiantes(estudiantes);
                carreras.add(ca);
            }
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
        return carreras;
    }
}
