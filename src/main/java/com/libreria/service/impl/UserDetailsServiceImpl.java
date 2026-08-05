package com.libreria.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.libreria.dto.UsuarioDto;
import com.libreria.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDto usuarioDto = usuarioRepository.obtenerUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return User
                .withUsername(usuarioDto.getUsername())
                .password(usuarioDto.getPassword())
                .roles(usuarioDto.getRol())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!usuarioDto.getEnabled())
                .build();

    }

}
