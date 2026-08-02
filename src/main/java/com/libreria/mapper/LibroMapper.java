package com.libreria.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.libreria.dto.LibroDto;
import com.libreria.entity.LibroEntity;

@Mapper
public interface LibroMapper {
    LibroDto convertirEntityADto(LibroEntity libroEntity);

    LibroEntity convertirDtoAEntity(LibroDto libroDto);

    List<LibroDto> convertirEntityADto(List<LibroEntity> libroEntity);

    List<LibroEntity> convertirDtoAEntity(List<LibroDto> libroDto);
}
