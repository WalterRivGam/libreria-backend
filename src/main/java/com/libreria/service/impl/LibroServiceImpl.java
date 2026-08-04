package com.libreria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.dto.LibroDto;
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

}
