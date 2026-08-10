package com.libreria.repository;

import java.util.Optional;

import com.libreria.dto.RefreshTokenDto;

public interface RefreshTokenRepository {
    Optional<RefreshTokenDto> obtenerPorToken(String token);

    RefreshTokenDto guardar(RefreshTokenDto token);

    void eliminar(RefreshTokenDto token);
}
