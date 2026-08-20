package com.libreria.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.AuthenticationException;

import com.libreria.controller.AuthController;
import com.libreria.dto.LoginRequestDto;
import com.libreria.dto.RefreshTokenDto;
import com.libreria.dto.TokenDto;
import com.libreria.service.RefreshTokenService;
import com.libreria.service.impl.JwtService;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserDetailsService userDetailsService;

    @Mock
    JwtService jwtService;

    @Mock
    RefreshTokenService refreshTokenService;

    @InjectMocks
    AuthController authController;

    @Mock
    UserDetails userDetails;

    @Test
    @DisplayName("Login exitoso")
    public void loginExitosoTest() {

        LoginRequestDto request = new LoginRequestDto("admin", "12345");

        RefreshTokenDto refreshToken = new RefreshTokenDto();
        refreshToken.setToken("refresh-token");

        given(userDetailsService.loadUserByUsername(request.getUsername()))
                .willReturn(userDetails);

        given(jwtService.generateToken(userDetails))
                .willReturn("access-token");

        given(refreshTokenService.createRefreshToken(request.getUsername()))
                .willReturn(refreshToken);

        ResponseEntity<TokenDto> resultado = authController.login(request);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());

        assertEquals("access-token", resultado.getBody().getAccessToken());
        assertEquals("refresh-token", resultado.getBody().getRefreshToken());

        then(authenticationManager).should().authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));
        then(userDetailsService).should().loadUserByUsername(request.getUsername());
        then(jwtService).should().generateToken(userDetails);
        then(refreshTokenService).should().createRefreshToken(request.getUsername());
    }

    @Test
    @DisplayName("Intento de login con nombre de usuario inexistente")
    public void loginUsernameNotFound() {
        LoginRequestDto request = new LoginRequestDto("admin", "12345");

        given(userDetailsService
                .loadUserByUsername(request.getUsername()))
                .willThrow(new UsernameNotFoundException("Usuario no encontrado"));

        assertThrows(AuthenticationException.class, () -> authController.login(request));

        then(jwtService).should(never()).generateToken(any());
        then(refreshTokenService).should(never()).createRefreshToken(any());
    }
}
