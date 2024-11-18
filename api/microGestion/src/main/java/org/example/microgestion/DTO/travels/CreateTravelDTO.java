package org.example.microgestion.DTO.travels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microgestion.DTO.payments.ResponseDebitDTO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTravelDTO {
    private Long id_travel;
    private Date initDate;
    private ResponseDebitDTO paymentDetail;
}
