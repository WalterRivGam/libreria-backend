package com.libreria.repository;

import java.util.Optional;

import com.libreria.dto.UsuarioDto;

public interface UsuarioRepository {
    public Optional<UsuarioDto> obtenerUsuario(String username);
}
