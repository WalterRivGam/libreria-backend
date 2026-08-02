package com.libreria.service;

import java.util.List;

import com.libreria.repository.dao.LibroDao;

public interface LibroService {
    List<LibroDao> listarLibros();
}
