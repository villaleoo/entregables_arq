package org.example.micromonopatines.DTO.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDTO {
    private Long totalAvailable;
    private Long totalNotAvailable;
}
