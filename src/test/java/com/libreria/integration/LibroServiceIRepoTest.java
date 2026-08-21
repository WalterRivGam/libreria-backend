package com.libreria.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.dto.LibroDto;
import com.libreria.service.LibroService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class LibroServiceIRepoTest {

    @Autowired
    private LibroService libroService;

    @Test
    public void registrarLibroExitoso() {
        LibroDto libroDto = new LibroDto();
        libroDto.setTitulo("titulo1");
        libroDto.setAutor("autor1");
        libroDto.setPrecio(BigDecimal.valueOf(150));

        LibroDto resultado = libroService.registrarLibro(libroDto);

        libroDto.setId(1);
        assertEquals(libroDto, resultado);
    }
}
