package org.example.reculp02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.reculp02.entity.Especialidad;

import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    // Verificar si ya existe una especialidad con el mismo nombre (ignorando mayúsculas/minúsculas)
    Optional<Especialidad> findByNombreIgnoreCase(String nombre);

    // Verificar si existe con nombre distinto al ID (para actualizaciones)
    @Query("SELECT e FROM Especialidad e WHERE LOWER(e.nombre) = LOWER(:nombre) AND e.id <> :id")
    Optional<Especialidad> findByNombreIgnoreCaseAndIdNot(@Param("nombre") String nombre, @Param("id") Long id);
}