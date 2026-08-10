package com.libreria.dto;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshTokenDto {
    private Long id;
    private String token;
    private Instant fechaExpiracion;
    private UsuarioDto usuario;
}
