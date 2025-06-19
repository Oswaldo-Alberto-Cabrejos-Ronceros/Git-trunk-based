package com.example.git_trunk_based.mapper;

import org.springframework.stereotype.Component;

import com.example.git_trunk_based.dto.RolDTO;
import com.example.git_trunk_based.entity.Rol;

@Component
public class RolMapper {
    public RolDTO mapToRolDTO(Rol rol) {
        return new RolDTO(rol.getId(), rol.getNombre(), rol.getDescripcion());
    }

    public Rol mapToRol(RolDTO rolDTO) {
        return new Rol(rolDTO.getId(), rolDTO.getNombre(), rolDTO.getDescripcion());
    }
}
