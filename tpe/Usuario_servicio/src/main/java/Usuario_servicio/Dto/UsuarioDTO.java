package Usuario_servicio.Dto;

import lombok.Getter;

@Getter
public class UsuarioDTO {
    private String nombre, apellido, email, user;
    private Integer celular, id_cuenta;

    public UsuarioDTO(String nombre, String apellido, String email, String user, Integer celular, Integer id_cuenta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.user = user;
        this.celular = celular;
        this.id_cuenta = id_cuenta;
    }
    public UsuarioDTO(String nombre, String apellido, String email, String user, Integer celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.user = user;
        this.celular = celular;
        this.id_cuenta = null;
    }
    public UsuarioDTO(){}
}
