package com.libreria.controller.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import jakarta.servlet.http.HttpServletRequest;
import com.libreria.dto.ErrorDto;
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
}
