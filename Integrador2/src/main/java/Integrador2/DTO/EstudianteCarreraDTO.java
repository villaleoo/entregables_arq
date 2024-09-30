package Integrador2.DTO;

import javax.persistence.Column;

public class EstudianteCarreraDTO {
    private Long idEstudiante;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private int documento;
    private String ciudad;
    private int nroLibreta;
    private Long idCarrera;
    private String nombreCarrera;

    public EstudianteCarreraDTO() {}

    public EstudianteCarreraDTO(Long idEstudiante, String nombre, String apellido, int edad, String genero, int documento, String ciudad, int nroLibreta, Long idCarrera, String nombreCarrera) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.documento = documento;
        this.ciudad = ciudad;
        this.nroLibreta = nroLibreta;
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNroLibreta() {
        return nroLibreta;
    }

    public void setNroLibreta(int nroLibreta) {
        this.nroLibreta = nroLibreta;
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }



    @Override
    public String toString() {
        return "EstudianteCarreraDTO{" +
                "idEstudiante=" + idEstudiante +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", documento=" + documento +
                ", ciudad='" + ciudad + '\'' +
                ", nroLibreta=" + nroLibreta +
                ", idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                '}';
    }
}
