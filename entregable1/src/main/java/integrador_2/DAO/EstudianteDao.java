package integrador_2.DAO;

import integrador_2.DTO.EstudianteDto;
import integrador_2.entidades.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDao {
    private String proyecto;
    public EstudianteDao(String pr) {
        this.proyecto = pr;
    }


    public void insertarEstudiante(Estudiante estudiante) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            em.persist(estudiante);

            em.getTransaction().commit();
            em.close();
            emf.close();
            System.out.println("Se inserto un estudiante exitosamente");
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
    }


    public void borrarEstudiante(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Estudiante estudiante = em.find(Estudiante.class, id);
            em.remove(estudiante);

            em.getTransaction().commit();
            em.close();
            emf.close();
            System.out.println("Se borró un estudiante exitosamente");
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
    }


    public EstudianteDto traerEstudiante(int id) {
        EstudianteDto estudiante = null;
        Estudiante aux = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            aux = em.find(Estudiante.class, id);
            estudiante = new EstudianteDto(aux.getNombre(), aux.getApellido(), aux.getGenero(),
                    aux.getCiudad_residencia(), aux.getDni(), aux.getNro_libreta());

            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
        return estudiante;
    }

    public EstudianteDto traerEstudiantePorNroLibreta(Integer nro_libreta) {
        EstudianteDto estudiante = null;
        Estudiante aux = null;
        String query = "select e from Estudiante e where e.nro_libreta = :nro_libreta";
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            aux = em.createQuery(query, Estudiante.class)
                    .setParameter("nro_libreta", nro_libreta).getSingleResult();
            if(aux != null) {
                estudiante = new EstudianteDto(aux.getNombre(), aux.getApellido(), aux.getGenero(),
                        aux.getCiudad_residencia(), aux.getDni(), aux.getNro_libreta());
            }
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
        return estudiante;
    }

    public List<EstudianteDto> traerEstudiantesPorGenero(String genero) {
        List<Estudiante> aux = null;
        List<EstudianteDto> estudiantes = new ArrayList<>();
        String query = "select e from Estudiante e where e.genero = :genero";
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            aux = em.createQuery(query, Estudiante.class)
                    .setParameter("genero", genero).getResultList();
            estudiantes = transferirDatos(aux, em);
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
        return estudiantes;
    }

    public List<EstudianteDto> traerEstudiantes() {
        List<Estudiante> auxiliar = null;
        List<EstudianteDto> estudiantes = new ArrayList<>();
        String query = "SELECT e FROM Estudiante e order by apellido";
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            auxiliar = em.createQuery(query, Estudiante.class).getResultList();
            estudiantes = transferirDatos(auxiliar, em);
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
        return estudiantes;
    }

    public List<EstudianteDto> filtrarCarreraCiudad(String carrera, String ciudad) {
        List<Estudiante> auxiliar = null;
        List<EstudianteDto> estudiantes = new ArrayList<>();
        String query = "select e from Estudiante e\n" +
                "join Inscripcion i \n" +
                "on e.id=i.estudiante.id\n" +
                "where e.ciudad_residencia=:ciudad\n" +
                "and i.carrera.id = (select c.id \n" +
                "                    from Carrera c\n" +
                "                    where nombre=:carrera)";
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.proyecto);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            auxiliar = em.createQuery(query, Estudiante.class)
                    .setParameter("ciudad", ciudad).setParameter("carrera", carrera)
                    .getResultList();
            estudiantes = transferirDatos(auxiliar, em);
        } catch (Exception e) {
            System.out.println("Algo salio mal "+e);
        }
        return estudiantes;
    }

    private List<EstudianteDto> transferirDatos(List<Estudiante> auxiliar, EntityManager em) {
        List<EstudianteDto> estudiantes = new ArrayList<>();
        for(Estudiante e : auxiliar) {
            EstudianteDto est = new EstudianteDto(e.getNombre(), e.getApellido(), e.getGenero(),
                    e.getCiudad_residencia(), e.getDni(), e.getNro_libreta());
            estudiantes.add(est);
        }
        return estudiantes;
    }
}
