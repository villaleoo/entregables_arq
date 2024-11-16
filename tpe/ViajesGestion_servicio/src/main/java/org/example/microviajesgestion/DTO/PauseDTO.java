package org.example.microviajesgestion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PauseDTO {
    private Long id_travel;
    private Date initPause;
}
