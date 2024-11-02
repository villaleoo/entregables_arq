package Usuario_servicio.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Cada usuario tendrá un nombre y debe registrar su
//número de celular, email válido, nombre y apellido.
@Entity
@Getter
public class Usuario {
    @Id
    private Integer id;
    @Setter
    private String nombre_usuario;
    @Setter
    private Integer nro_celular;
    @Setter
    private String email;
    @Setter
    private String nombre;
    @Setter
    private String apellido;
    public Usuario(){}
    public Usuario(Integer id, String user, Integer tel, String mail, String nombre, String apellido) {
        this.id = id;
        this.nombre_usuario = user;
        this.nro_celular = tel;
        this.email = mail;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
