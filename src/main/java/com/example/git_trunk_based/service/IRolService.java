package com.example.git_trunk_based.service;

import java.util.List;
import java.util.Optional;

import com.example.git_trunk_based.dto.RolDTO;

public interface IRolService {

    public List<RolDTO> listarRoles();

    public Optional<RolDTO> obtenerPorId(Long id);

    public RolDTO guardar(RolDTO rolDTO);

    public RolDTO actualizar(Long id, RolDTO nuevoRol);

    public void eliminar(Long id);
}