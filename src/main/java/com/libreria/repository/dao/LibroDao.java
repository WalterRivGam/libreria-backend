package com.libreria.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libreria.entity.LibroEntity;

public interface LibroDao extends JpaRepository<LibroEntity, Integer> {

}
