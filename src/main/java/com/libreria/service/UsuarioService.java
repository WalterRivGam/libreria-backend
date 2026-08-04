package com.libreria.service;

import com.libreria.dto.UsuarioDto;

public interface UsuarioService {
    public UsuarioDto obtenerUsuario(String username);

    public UsuarioDto registrarUsuario(UsuarioDto usuario);
}
