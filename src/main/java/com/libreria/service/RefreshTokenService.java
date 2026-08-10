package com.libreria.service;

import java.util.Optional;

import com.libreria.dto.RefreshTokenDto;

public interface RefreshTokenService {

    public RefreshTokenDto createRefreshToken(String username);

    public RefreshTokenDto verificarExpiracion(RefreshTokenDto token);

    public Optional<RefreshTokenDto> encontrarPorToken(String token);
}
