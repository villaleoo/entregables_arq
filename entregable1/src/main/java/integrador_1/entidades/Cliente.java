package integrador_1.entidades;


public class Cliente {

    private int idCliente;
    private String nombre;
    private String email;
    public Cliente() {
        super();
    }

    public Cliente(int idCliente, String nombre, String email) {
        this.email = email;
        this.idCliente = idCliente;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return STR."Cliente{idCliente=\{idCliente}, nombre='\{nombre}', email='\{email}'}";
    }

}
