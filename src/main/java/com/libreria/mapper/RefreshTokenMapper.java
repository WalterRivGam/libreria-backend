package com.libreria.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.libreria.dto.RefreshTokenDto;
import com.libreria.entity.RefreshTokenEntity;

@Mapper
public interface RefreshTokenMapper {
    RefreshTokenDto convertirDeEntityADto(RefreshTokenEntity refreshTokenEntity);

    List<RefreshTokenDto> convertirDeEntityADto(List<RefreshTokenEntity> refreshTokenEntity);

    RefreshTokenEntity convertirDeDtoAEntity(RefreshTokenDto refreshTokenDto);

    List<RefreshTokenEntity> convertirDeDtoAEntity(List<RefreshTokenDto> refreshTokenDto);
}
