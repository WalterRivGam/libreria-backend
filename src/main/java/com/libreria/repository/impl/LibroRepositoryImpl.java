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
@RequiredArgsConstructor
public class LibroRepositoryImpl implements LibroRepository {

    private final LibroDao libroDao;
    private LibroMapper libroMapper = Mappers.getMapper(LibroMapper.class);

    @Override
    public List<LibroDto> listarLibros() {
        return libroMapper.convertirEntityADto(libroDao.findAll());
    }

}
