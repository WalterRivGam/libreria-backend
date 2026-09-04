package com.libreria.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

import java.math.BigDecimal;

import com.libreria.dto.LibroDto;
import com.libreria.exception.DatosInvalidosException;
import com.libreria.exception.LibroNoEncontradoException;
import com.libreria.repository.LibroRepository;
import com.libreria.service.impl.LibroServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

    @Mock
    LibroRepository libroRepository;

    @InjectMocks
    LibroServiceImpl libroService;

    @Test
    @DisplayName("Prueba buscar libro con id inexistente y lanza LibroNoEncontradoException")
    void listarLibroNoEncontradoTest() {
        Integer idLibroInexistente = 999;

        given(libroRepository.listarLibro(idLibroInexistente))
                .willThrow(new LibroNoEncontradoException("Libro con ID " +
                        idLibroInexistente + " no encontrado"));

        assertThrows(LibroNoEncontradoException.class,
                () -> libroService.listarLibro(idLibroInexistente));
    }

    @Test
    @DisplayName("Prueba registrar libro con autor null")
    public void registrarLibroAutorNull() {
        LibroDto libro = new LibroDto();
        libro.setAutor(null);
        libro.setTitulo("titulo");
        libro.setPrecio(BigDecimal.valueOf(100));

        assertThrows(DatosInvalidosException.class,
                () -> libroService.registrarLibro(libro));

        then(libroRepository).should(never()).registrarLibro(any());
    }

    @Test
    @DisplayName("Prueba para registrar libro con datos correctos")
    public void registrarLibroCorrecto() {
        LibroDto libro = new LibroDto();
        libro.setAutor("Autor");
        libro.setTitulo("titulo");
        libro.setPrecio(BigDecimal.valueOf(100));

        given(libroRepository.registrarLibro(libro)).willReturn(libro);

        LibroDto respuesta = libroService.registrarLibro(libro);

        assertEquals(libro, respuesta);

        then(libroRepository).should().registrarLibro(libro);
    }
}
