package org.example.integrador_3.repositorio;

import org.example.integrador_3.persistencia.DTO.CarreraDto;
import org.example.integrador_3.persistencia.modelo.Carrera;
import org.example.integrador_3.persistencia.DTO.ReporteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepo extends JpaRepository<Carrera, Integer> {

    String queryCarreras = "select new CarreraDto(c.id, c.nombre, c.anios_duracion, count(*) as cant)\n" +
            "from Carrera c join Inscripcion i \n" +
            "on c.id=i.carrera.id\n" +
            "group by c.id\n" +
            "order by cant desc";
    @Query("select new org.example.integrador_3.persistencia.DTO.CarreraDto(c.id, c.nombre, c.anios_duracion, count(*))\n" +
            "from Carrera c join Inscripcion i \n" +
            "on c.id=i.carrera.id\n" +
            "group by c.id\n" +
            "order by count(*) desc")
    public List<CarreraDto> traerCarrerasConEstudiantes();

    String queryReporte = "select c.nombre, i.estudiante_id, i.anio_inicio,(i.anio_inicio+i.anio_graduacion) as graduacion\n" +
        "from carrera c\n" +
        "join inscripcion i on c.id=i.carrera_id\n" +
        "order by c.nombre asc, i.anio_inicio asc";
    @Query("select new org.example.integrador_3.persistencia.DTO.ReporteDto(c.nombre, i.estudiante.id, i.anio_inicio,(i.anio_inicio+i.anio_Graduacion))\n" +
            "from Carrera c\n" +
            "join Inscripcion i on c.id=i.carrera.id\n" +
            "order by c.nombre asc, i.anio_inicio asc")
    public List<ReporteDto> traerReporte();

}
