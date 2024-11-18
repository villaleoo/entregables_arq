package org.example.microgestion.DTO.travels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitTravelDTO {
    private Long id_user;
    private Long id_account;
    private String id_monopatin;
    private Long id_stop_end;

}