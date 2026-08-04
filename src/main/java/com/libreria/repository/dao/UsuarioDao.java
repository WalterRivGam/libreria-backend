package com.libreria.repository.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libreria.entity.UsuarioEntity;

public interface UsuarioDao extends JpaRepository<UsuarioEntity, Integer> {
    public Optional<UsuarioEntity> findByUsername(String username);

    public boolean existsByUsername(String username);
}
