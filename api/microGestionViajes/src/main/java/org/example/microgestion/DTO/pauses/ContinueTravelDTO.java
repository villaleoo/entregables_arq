package org.example.microgestion.DTO.pauses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microgestion.DTO.payments.ResponseDebitDTO;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContinueTravelDTO {
    private long minutesCharged;
    private long totalPauseInMin;
    private ResponseDebitDTO debitDetail;
}
