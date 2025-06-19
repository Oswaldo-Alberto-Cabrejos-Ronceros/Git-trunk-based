package com.example.git_trunk_based.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.git_trunk_based.entity.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    boolean existsByNombre(String nombre);

    Optional<Rol> findById(Long id);
}
