package org.example.micromonopatines.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.micromonopatines.DTO.externals.StopDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMonoDTO {
    @JsonProperty("isAvailable")
    @NotNull(message = "Debe incluir si el monopatin esta disponible.")
    private boolean isAvailable;
    @NotNull(message = "Debe incluir la parada asignada para el monopatin.")
    private StopDTO stopAssign;
}