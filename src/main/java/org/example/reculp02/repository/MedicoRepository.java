package org.example.reculp02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.reculp02.entity.Medico;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> { // Long sigue siendo idMedico

    // Cambiado de Long a String
    Optional<Medico> findByMedCmp(String medCmp);

    // Cambiado de Long a String
    @Query("SELECT m FROM Medico m WHERE m.medCmp = :medCmp AND m.idMedico <> :id")
    Optional<Medico> findByMedCmpAndIdMedicoNot(@Param("medCmp") String medCmp, @Param("id") Long id);
}