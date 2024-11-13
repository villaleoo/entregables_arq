package org.example.microgestion.DTO.externals.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id_user;
    private String name;
    private String surname;
    private String phone;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;
    private CuentaDTO account;
}