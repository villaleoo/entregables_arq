package org.example.microgestion.DTO.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportTotalBilledDTO {
    private Integer monthInit;
    private Integer monthEnd;
    private Integer year;
    private Long totalInPesos;
}
