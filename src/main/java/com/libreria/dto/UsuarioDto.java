package com.libreria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private String rol;
}
