package com.libreria.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.libreria.dto.ErrorDto;
import com.libreria.exception.UsuarioNoEncontradoException;
import com.libreria.exception.UsuarioYaRegistradoException;

@RestControllerAdvice
public class UsuarioControllerAdvice {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ErrorDto> usuarioNoEncontradoExceptionHandler(UsuarioNoEncontradoException excepcion) {
        ErrorDto error = new ErrorDto("No se encontró usuario con nombre de usuario: " + excepcion.getUsername());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsuarioYaRegistradoException.class)
    public ResponseEntity<ErrorDto> usuarioYaRegistradoExceptionHandler(UsuarioYaRegistradoException exception) {
        ErrorDto error = new ErrorDto(
                "No se puede registrar usuario, ya existe el nombre de usuario: " + exception.getUsername());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
