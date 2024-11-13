package org.example.micromonopatines.DTO.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportUsagePauseDTO{
    private String id_monopatin;
    private Long total_kms;
    private Long total_travels;
    private Long total_min_pause;
}
