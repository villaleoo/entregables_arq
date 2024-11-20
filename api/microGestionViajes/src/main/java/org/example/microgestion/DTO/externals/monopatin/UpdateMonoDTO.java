package org.example.microgestion.DTO.externals.monopatin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microgestion.DTO.externals.stops.StopDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMonoDTO {
    @JsonProperty("isAvailable")
    private boolean isAvailable;
    private StopDTO stopAssign;
}
