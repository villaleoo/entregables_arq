package org.example.micromonopatines.DTO.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportUsageDTO {
    private String id_monopatin;
    private Long total_kms;
    private Long total_travels;

}
