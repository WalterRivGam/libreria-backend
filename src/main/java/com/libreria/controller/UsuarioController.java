package com.libreria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.dto.UsuarioDto;
import com.libreria.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UsuarioDto> obtenerUsuario(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.obtenerUsuario(username));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> registrarUsuario(@RequestBody UsuarioDto usuario) {
        usuarioService.registrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
