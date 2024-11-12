package org.example.microgestion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitTravelDTO {
    private Long id_user;
    private String id_monopatin;
    private Long id_stop_init;
    private Long id_stop_end;
}
