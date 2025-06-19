package com.example.git_trunk_based.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.git_trunk_based.dto.RolDTO;
import com.example.git_trunk_based.service.IRolService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final IRolService rolService;

    @Autowired
    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<RolDTO>> listarRoles() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @PostMapping
    public ResponseEntity<RolDTO> crearRol(@RequestBody @Valid RolDTO rol) {
        RolDTO creado = rolService.guardar(rol);
        return ResponseEntity.status(201).body(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> obtenerRolPorId(@PathVariable Long id) {
        return rolService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> actualizarRol(@PathVariable Long id, @RequestBody @Valid RolDTO rolActualizado) {
        RolDTO actualizado = rolService.actualizar(id, rolActualizado);
        return (actualizado != null)
                ? ResponseEntity.ok(actualizado)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
