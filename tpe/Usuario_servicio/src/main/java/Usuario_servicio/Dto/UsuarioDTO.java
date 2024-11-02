package Usuario_servicio.Dto;

public class UsuarioDTO {
    private String nombre, apellido, email, user;
    private Integer celular;

    public UsuarioDTO(String nombre, String apellido, String email, String user, Integer celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.user = user;
        this.celular = celular;
    }
}
