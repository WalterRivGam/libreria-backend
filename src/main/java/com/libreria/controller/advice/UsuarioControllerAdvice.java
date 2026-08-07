package com.libreria.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.core.AuthenticationException;

import com.libreria.dto.ErrorDto;
import com.libreria.exception.UsuarioNoEncontradoException;
import com.libreria.exception.UsuarioYaRegistradoException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class UsuarioControllerAdvice {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ErrorDto> usuarioNoEncontradoExceptionHandler(UsuarioNoEncontradoException excepcion,
            HttpServletRequest request) {

        String mensaje = "No se encontró usuario con nombre de usuario: " + excepcion.getUsername();

        ErrorDto error = new ErrorDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                mensaje,
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsuarioYaRegistradoException.class)
    public ResponseEntity<ErrorDto> usuarioYaRegistradoExceptionHandler(UsuarioYaRegistradoException exception,
            HttpServletRequest request) {

        String mensaje = "No se puede registrar nombre de usuario ya existente: " + exception.getUsername();

        ErrorDto error = new ErrorDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                mensaje,
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> errorDeAutenticacion(AuthenticationException exception,
            HttpServletRequest request) {

        String mensaje = "No se pudo autenticar al usuario. Nombre usuario o contraseña incorrecta.";

        ErrorDto error = new ErrorDto(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                mensaje,
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
