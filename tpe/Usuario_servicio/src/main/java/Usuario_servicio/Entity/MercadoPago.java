package Usuario_servicio.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity

public class MercadoPago {
   @Id
   private Integer id;
   @Setter
   private Integer saldo;
   public MercadoPago() {}
   public MercadoPago(Integer id) {
      this.id = id;
      this.saldo = 0;
   }
}
