package com.libreria.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.libreria.dto.RefreshTokenDto;
import com.libreria.repository.RefreshTokenRepository;
import com.libreria.repository.UsuarioRepository;
import com.libreria.service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refresh.duration}")
    private Long refreshTokenDurationMs;

    private RefreshTokenRepository refreshTokenRepository;

    private UsuarioRepository usuarioRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UsuarioRepository usuarioRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public RefreshTokenDto createRefreshToken(String username) {
        RefreshTokenDto refreshToken = new RefreshTokenDto();

        refreshToken.setUsuario(usuarioRepository.obtenerUsuario(username).get());
        refreshToken.setFechaExpiracion(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.guardar(refreshToken);
    }

    @Override
    public RefreshTokenDto verifyExpiration(RefreshTokenDto token) {
        if (token.getFechaExpiracion().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.eliminar(token);
            throw new RuntimeException("El Refresh Token ha expirado. Inicie sesión nuevamente.");
        }
        return token;
    }

    public Optional<RefreshTokenDto> findByToken(String token) {
        return refreshTokenRepository.obtenerPorToken(token);
    }
}