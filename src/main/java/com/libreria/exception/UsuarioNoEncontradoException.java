package com.libreria.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioNoEncontradoException extends RuntimeException {

    private String username;

}
