package com.libreria.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.libreria.dto.LibroDto;
import com.libreria.exception.DatosInvalidosException;
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
        if (sonDatosValidos(libroDto)) {
            return libroRepository.registrarLibro(libroDto);
        }
        throw new DatosInvalidosException("Los datos del libro no son válidos");
    }

    @Override
    public LibroDto listarLibro(Integer idLibro) {
        Optional<LibroDto> libro = libroRepository.listarLibro(idLibro);
        return libro.orElseThrow(() -> new LibroNoEncontradoException("Libro con ID " + idLibro + " no encontrado"));
    }

    @Override
    @Transactional
    public LibroDto actualizarLibro(LibroDto libroDto, Integer idLibro) {
        Optional<LibroDto> libroExistente = libroRepository.listarLibro(idLibro);
        if (libroExistente.isPresent()) {
            libroDto.setId(idLibro);
            return libroRepository.actualizarLibro(libroDto);
        } else {
            throw new LibroNoEncontradoException("Libro con ID " + idLibro + " no encontrado");
        }
    }

    private boolean sonDatosValidos(LibroDto libro) {
        if (libro == null || !StringUtils.hasText(libro.getAutor()) || !StringUtils.hasText(libro.getTitulo())) {
            return false;
        }
        if (libro.getPrecio() != null && libro.getPrecio().compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }
}
