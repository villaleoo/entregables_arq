package org.example.micromonopatines.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.micromonopatines.DTO.externals.StopDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMonoDTO {
    @JsonProperty("isAvailable")
    private boolean isAvailable;
    private StopDTO stopAssign;
}