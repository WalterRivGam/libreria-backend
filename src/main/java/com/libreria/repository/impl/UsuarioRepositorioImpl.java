package com.libreria.repository.impl;

import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import com.libreria.dto.UsuarioDto;
import com.libreria.mapper.UsuarioMapper;
import com.libreria.repository.UsuarioRepository;
import com.libreria.repository.dao.UsuarioDao;

@Repository
public class UsuarioRepositorioImpl implements UsuarioRepository {

    private final UsuarioDao usuarioDao;

    private UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);

    public UsuarioRepositorioImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Optional<UsuarioDto> obtenerUsuario(String username) {
        return usuarioDao.findByUsername(username)
                .map(usuarioMapper::convertirEntityADto);
    }

}
