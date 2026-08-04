package com.libreria.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder encoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
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
        usuario.setPassword(encoder.encode(usuario.getPassword()));

        return usuarioRepository.registrarUsuario(usuario);
    }
}