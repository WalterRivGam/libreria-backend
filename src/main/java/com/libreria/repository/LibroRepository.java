package com.libreria.repository;

import java.util.List;

import com.libreria.dto.LibroDto;

public interface LibroRepository {
    public List<LibroDto> listarLibros();

    public LibroDto registrarLibro(LibroDto libroDto);
}
