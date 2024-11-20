package org.example.micromonopatines.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonoDTO {
    @JsonProperty("isAvailable")
    @NotNull(message = "Debe incluir si el monopatin esta disponible.")
    private boolean isAvailable;
    @NotNull(message = "Debe incluir la parada donde va a ubicarse el monopatin.")
    private Long id_stop;
    @NotNull(message = "Debe incluir los kilometros del monopatin.")
    private double kms;
}
