package Integrador1.DTO;

public class ClienteDTO {
    private int idCliente;
    private String nombre;
    private String email;
    private int cantFacturaciones;

    public ClienteDTO(int idCliente, String nombre, String email, int cantFacturaciones) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.cantFacturaciones = cantFacturaciones;
    }

    public int getIdCliente() {
        return idCliente;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCantFacturaciones() {
        return cantFacturaciones;
    }

    public void setCantFacturaciones(int cantFacturaciones) {
        this.cantFacturaciones = cantFacturaciones;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", cantFacturaciones=" + cantFacturaciones +
                '}';
    }
}
