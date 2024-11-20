package org.example.microgestion.DTO.pauses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PauseResponseDTO {
    private Long id_travel;
    private Date initPause;
}
