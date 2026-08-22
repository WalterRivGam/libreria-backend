package com.libreria.service;

import java.util.List;

import com.libreria.dto.LibroDto;

public interface LibroService {
    List<LibroDto> listarLibros();

    LibroDto registrarLibro(LibroDto libroDto);

    LibroDto listarLibro(Integer idLibro);
}
