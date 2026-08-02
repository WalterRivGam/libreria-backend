package com.libreria.repository.impl;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import com.libreria.dto.LibroDto;
import com.libreria.mapper.LibroMapper;
import com.libreria.repository.LibroRepository;
import com.libreria.repository.dao.LibroDao;

import lombok.RequiredArgsConstructor;

@Repository
public class LibroRepositoryImpl implements LibroRepository {

    private LibroDao libroDao;
    private LibroMapper libroMapper = Mappers.getMapper(LibroMapper.class);

    public LibroRepositoryImpl(LibroDao libroDao) {
        this.libroDao = libroDao;
    }

    @Override
    public List<LibroDto> listarLibros() {
        return libroMapper.convertirEntityADto(libroDao.findAll());
    }

}
