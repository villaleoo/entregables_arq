package org.example.micromonopatines.DTO.externals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalTravelsDTO {
    private Long total_travels;
    private Long total_min_traveled;
    private Long total_min_pause;
}
