package com.example.git_trunk_based.rol;

import com.example.git_trunk_based.entity.Rol;
import com.example.git_trunk_based.repository.RolRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RolRepositoryTest {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Guardar rol con estado verdadero debe permitir encontrarlo por nombre")
    void guardarRol__debeEncontrarloPorNombre() {
        Rol rol = new Rol();
        rol.setNombre("ADMIN");
        rol.setDescripcion("Administrador del sistema");
        rolRepository.save(rol);
        entityManager.flush();
        entityManager.clear();

        boolean existe = rolRepository.existsByNombre("PACIENTE");

        assertThat(existe).isTrue();
    }

    @Test
    @DisplayName("Guardar rol y buscar por ID debe encontrar el rol")
    void guardarRol_debeEncontrarloPorId() {
        Rol rol = new Rol();
        rol.setNombre("RECEPCIONISTA");
        rol.setDescripcion("Recepcionista de la clínica");
        Rol rolGuardado = rolRepository.save(rol);
        entityManager.flush();
        entityManager.clear();

        Optional<Rol> resultado = rolRepository.findById(rolGuardado.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("RECEPCIONISTA");
    }
}
