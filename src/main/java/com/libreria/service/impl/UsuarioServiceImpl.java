package com.libreria.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.libreria.dto.UsuarioDto;
import com.libreria.exception.UsuarioNoEncontradoException;
import com.libreria.exception.UsuarioYaRegistradoException;
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

    @Override
    public UsuarioDto registrarUsuario(UsuarioDto usuario) {
        if (usuarioRepository.existeConNombreDeUsuario(usuario.getUsername())) {
            throw new UsuarioYaRegistradoException(usuario.getUsername());
        }
        usuario.setEnabled(true);
        if (!StringUtils.hasText(usuario.getRol())) {
            usuario.setRol("USUARIO");
        }
        return usuarioRepository.registrarUsuario(usuario);
    }
}