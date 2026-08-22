package com.libreria.repository;

import java.util.List;
import java.util.Optional;

import com.libreria.dto.LibroDto;

public interface LibroRepository {
    public List<LibroDto> listarLibros();

    public LibroDto registrarLibro(LibroDto libroDto);

    public Optional<LibroDto> listarLibro(Integer idLibro);

    public LibroDto actualizarLibro(LibroDto libroDto);
}
