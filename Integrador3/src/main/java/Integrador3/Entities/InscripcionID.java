package Integrador3.Entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionID implements Serializable {
    private Long documento;
    private Long idCarrera;
}
