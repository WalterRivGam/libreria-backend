package com.libreria.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LibroDto {

    private Integer id;
    private String titulo;
    private String autor;
    private BigDecimal precio;

}
