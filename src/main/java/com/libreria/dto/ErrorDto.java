package com.libreria.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDto {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String mensaje;
    private String path;
}
