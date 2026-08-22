package com.libreria.repository.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import com.libreria.dto.LibroDto;
import com.libreria.entity.LibroEntity;
import com.libreria.mapper.LibroMapper;
import com.libreria.repository.LibroRepository;
import com.libreria.repository.dao.LibroDao;

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

    @Override
    public LibroDto registrarLibro(LibroDto libroDto) {
        LibroEntity libroEntity = libroMapper.convertirDtoAEntity(libroDto);
        return libroMapper.convertirEntityADto(libroDao.save(libroEntity));
    }

    @Override
    public Optional<LibroDto> listarLibro(Integer idLibro) {
        return libroDao.findById(idLibro).map(libroMapper::convertirEntityADto);
    }

    @Override
    public LibroDto actualizarLibro(LibroDto libroDto) {
        LibroEntity libroEntity = libroMapper.convertirDtoAEntity(libroDto);
        return libroMapper.convertirEntityADto(libroDao.save(libroEntity));
    }

}
