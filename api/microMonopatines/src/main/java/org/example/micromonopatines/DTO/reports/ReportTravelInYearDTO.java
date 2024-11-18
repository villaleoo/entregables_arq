package org.example.micromonopatines.DTO.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportTravelInYearDTO {
    private String id_monopatin;
    private Long totalTravelsInYear;
}