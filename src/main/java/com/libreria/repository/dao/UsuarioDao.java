package com.libreria.repository.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.libreria.entity.UsuarioEntity;

public interface UsuarioDao extends JpaRepository<UsuarioEntity, Integer> {
    @Query(value = "SELECT u FROM UsuarioEntity u WHERE u.username = :username")
    Optional<UsuarioEntity> findByUsername(@Param("username") String username);
}
