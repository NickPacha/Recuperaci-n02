package org.example.reculp02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.reculp02.entity.Especialidad;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdEspecialidadNot(String nombre, Long id);
}
