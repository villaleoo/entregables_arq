package org.example.microgestion.DTO.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportMonopatinesDTO {
    private String id_monopatin;
    private Long totalTravelsInYear;
}