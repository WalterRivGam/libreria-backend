package com.libreria.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioYaRegistradoException extends RuntimeException {
    private String username;
}
