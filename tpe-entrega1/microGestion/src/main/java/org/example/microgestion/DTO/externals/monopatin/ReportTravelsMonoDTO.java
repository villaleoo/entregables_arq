package org.example.microgestion.DTO.externals.monopatin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportTravelsMonoDTO {
    private long total_travels;
    private long total_min_pause;
}
