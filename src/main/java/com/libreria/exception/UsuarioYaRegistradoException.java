package com.libreria.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioYaRegistradoException extends RuntimeException {
    public UsuarioYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}
