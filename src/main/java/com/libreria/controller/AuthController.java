package com.libreria.controller;

import com.libreria.dto.LoginRequestDto;
import com.libreria.dto.RefreshRequestDto;
import com.libreria.dto.RefreshTokenDto;
import com.libreria.dto.TokenDto;
import com.libreria.service.RefreshTokenService;
import com.libreria.service.impl.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());

        String jwt = jwtService.generateToken(userDetails);

        RefreshTokenDto refreshToken = refreshTokenService
                .createRefreshToken(request.getUsername());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new TokenDto(jwt, refreshToken.getToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(@RequestBody RefreshRequestDto request) {

        String refreshToken = request.getToken();

        return refreshTokenService.encontrarPorToken(refreshToken)
                .map(refreshTokenService::verificarExpiracion)
                .map(RefreshTokenDto::getUsuario)
                .map(usuario -> {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsername());
                    String nuevoJwt = jwtService.generateToken(userDetails);

                    return ResponseEntity.ok(new TokenDto(nuevoJwt, refreshToken));
                })
                .orElseThrow(() -> new RuntimeException("Refresh token no encontrado"));
    }
}
