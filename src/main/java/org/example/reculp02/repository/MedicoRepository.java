package org.example.reculp02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.reculp02.entity.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByCmp(String cmp);
    boolean existsByCmpAndIdMedicoNot(String cmp, Long id);
}
