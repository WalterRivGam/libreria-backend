package com.libreria.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.dto.LibroDto;
import com.libreria.exception.LibroNoEncontradoException;
import com.libreria.repository.LibroRepository;
import com.libreria.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<LibroDto> listarLibros() {
        return libroRepository.listarLibros();
    }

    @Override
    @Transactional
    public LibroDto registrarLibro(LibroDto libroDto) {
        return libroRepository.registrarLibro(libroDto);
    }

    @Override
    public LibroDto listarLibro(Integer idLibro) {
        Optional<LibroDto> libro = libroRepository.listarLibro(idLibro);
        return libro.orElseThrow(() -> new LibroNoEncontradoException("Libro con ID " + idLibro + " no encontrado"));
    }

}
