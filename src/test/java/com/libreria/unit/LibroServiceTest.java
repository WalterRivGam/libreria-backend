package com.libreria.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.libreria.exception.LibroNoEncontradoException;
import com.libreria.repository.LibroRepository;
import com.libreria.service.LibroService;
import com.libreria.service.impl.LibroServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

    @Mock
    LibroRepository libroRepository;

    @InjectMocks
    LibroServiceImpl libroService;

    @Test
    @DisplayName("Intenta buscar libro con id inexistente y lanza LibroNoEncontradoException")
    void listarLibroNoEncontradoTest() {
        Integer idLibroInexistente = 999;

        given(libroRepository.listarLibro(idLibroInexistente))
                .willThrow(new LibroNoEncontradoException("Libro con ID " +
                        idLibroInexistente + " no encontrado"));

        assertThrows(LibroNoEncontradoException.class,
                () -> libroService.listarLibro(idLibroInexistente));
    }
}
