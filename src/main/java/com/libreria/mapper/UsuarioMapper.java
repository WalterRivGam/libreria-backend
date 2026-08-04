package com.libreria.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.libreria.dto.UsuarioDto;
import com.libreria.entity.UsuarioEntity;

@Mapper
public interface UsuarioMapper {
    UsuarioDto convertirEntityADto(UsuarioEntity usuarioEntity);

    UsuarioEntity convertirDtoAEntity(UsuarioDto usuarioDto);

    List<UsuarioDto> convertirEntityADto(List<UsuarioEntity> usuarioEntity);

    List<UsuarioEntity> convertirDtoAEntity(List<UsuarioDto> usuarioDto);
}
