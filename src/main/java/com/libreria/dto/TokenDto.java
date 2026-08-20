package com.libreria.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
