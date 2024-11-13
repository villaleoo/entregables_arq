package org.example.microgestion.DTO.pauses;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microgestion.model.Viaje;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PauseDTO {
    private Long id_pause;
    private Date date_init_pause;
    private Date date_end_pause;
    private Integer minPause;
}
