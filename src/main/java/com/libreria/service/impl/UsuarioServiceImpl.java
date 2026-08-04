package com.libreria.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.libreria.dto.UsuarioDto;
import com.libreria.exception.UsuarioNoEncontradoException;
import com.libreria.repository.UsuarioRepository;
import com.libreria.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDto obtenerUsuario(String username) {
        return usuarioRepository.obtenerUsuario(username).orElseThrow(() -> new UsuarioNoEncontradoException(username));
    }

}
