package com.libreria.service;

import java.util.Optional;

import com.libreria.dto.UsuarioDto;

public interface UsuarioService {
    public UsuarioDto obtenerUsuario(String username);
}
