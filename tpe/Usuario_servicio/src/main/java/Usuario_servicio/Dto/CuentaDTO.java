package Usuario_servicio.Dto;

import Usuario_servicio.Entity.Usuario;
import lombok.Getter;

import java.util.List;

@Getter
public class CuentaDTO {
    private Integer id;
    private String fecha_alta;
    private Integer mercadopago;
    public CuentaDTO(Integer id, String fecha, Integer mp) {
        this.id = id;
        this.fecha_alta = fecha;
        this.mercadopago = mp;
    }

    public CuentaDTO() {}
}
