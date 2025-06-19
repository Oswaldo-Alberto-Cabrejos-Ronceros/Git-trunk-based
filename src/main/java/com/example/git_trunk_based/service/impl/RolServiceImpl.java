package com.example.git_trunk_based.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.git_trunk_based.dto.RolDTO;
import com.example.git_trunk_based.entity.Rol;
import com.example.git_trunk_based.mapper.RolMapper;
import com.example.git_trunk_based.repository.RolRepository;
import com.example.git_trunk_based.service.IRolService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements IRolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RolDTO> listarRoles() {
        return rolRepository.findAll().stream().map(rolMapper::mapToRolDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RolDTO> obtenerPorId(Long id) {
        return rolRepository.findById(id).map(rolMapper::mapToRolDTO);
    }

    @Transactional
    @Override
    public RolDTO guardar(RolDTO rolDTO) {

        if (rolRepository.existsByNombre(rolDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe un rol con el nombre ingresado");
        }

        Rol rol = rolMapper.mapToRol(rolDTO);
        Rol savedRol = rolRepository.save(rol);
        return rolMapper.mapToRolDTO(savedRol);
    }

    @Transactional
    @Override
    public RolDTO actualizar(Long id, RolDTO rol) {
        Rol rolExisting = rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un rol con el id: " + id));

        boolean nombreDuplicado = rolRepository.existsByNombre(rol.getNombre())
                && !rolExisting.getNombre().equalsIgnoreCase(rol.getNombre());

        if (nombreDuplicado) {
            throw new IllegalArgumentException("Ya existe un rol con el nombre ingresado");
        }

        rolExisting.setNombre(rol.getNombre());
        rolExisting.setDescripcion(rol.getDescripcion());
        Rol savedRol = rolRepository.save(rolExisting);
        return rolMapper.mapToRolDTO(savedRol);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un rol con el id: " + id));
        rolRepository.delete(rol);
    }
}
