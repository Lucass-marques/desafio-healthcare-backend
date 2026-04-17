package desafio.healthtech.care.repository;

import desafio.healthtech.care.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByAtivoTrue();
}
