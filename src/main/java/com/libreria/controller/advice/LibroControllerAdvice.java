package com.libreria.controller.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import jakarta.servlet.http.HttpServletRequest;
import com.libreria.dto.ErrorDto;
import com.libreria.exception.DatosInvalidosException;
import com.libreria.exception.LibroNoEncontradoException;

@RestControllerAdvice
public class LibroControllerAdvice {
    @ExceptionHandler(LibroNoEncontradoException.class)
    public ResponseEntity<ErrorDto> libroNoEncontradoExceptionHandler(LibroNoEncontradoException excepcion,
            HttpServletRequest request) {
        ErrorDto error = new ErrorDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                excepcion.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DatosInvalidosException.class)
    public ResponseEntity<ErrorDto> datosInvalidosExceptionHandler(DatosInvalidosException exception,
            HttpServletRequest request) {
        ErrorDto error = new ErrorDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
