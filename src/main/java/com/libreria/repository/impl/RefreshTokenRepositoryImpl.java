package com.libreria.repository.impl;

import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import com.libreria.dto.RefreshTokenDto;
import com.libreria.mapper.RefreshTokenMapper;
import com.libreria.repository.RefreshTokenRepository;
import com.libreria.repository.dao.RefreshTokenDao;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private RefreshTokenDao refrehTokenDao;
    private RefreshTokenMapper mapper = Mappers.getMapper(RefreshTokenMapper.class);

    public RefreshTokenRepositoryImpl(RefreshTokenDao refrehTokenDao) {
        this.refrehTokenDao = refrehTokenDao;
    }

    @Override
    public Optional<RefreshTokenDto> obtenerPorToken(String token) {
        return refrehTokenDao.findByToken(token).map(mapper::convertirDeEntityADto);
    }

    @Override
    public RefreshTokenDto guardar(RefreshTokenDto token) {
        return mapper.convertirDeEntityADto(refrehTokenDao.save(mapper.convertirDeDtoAEntity(token)));
    }

    @Override
    public void eliminar(RefreshTokenDto token) {
        refrehTokenDao.delete(mapper.convertirDeDtoAEntity(token));
    }

}
