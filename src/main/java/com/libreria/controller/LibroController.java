package com.libreria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libreria.dto.LibroDto;
import com.libreria.service.LibroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/libros")
public class LibroController {
    private LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<LibroDto>> listarLibros() {
        return ResponseEntity.status(HttpStatus.OK).body(libroService.listarLibros());
    }
}
