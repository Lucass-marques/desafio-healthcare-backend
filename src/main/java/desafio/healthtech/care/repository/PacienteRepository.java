package desafio.healthtech.care.repository;

import desafio.healthtech.care.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByAtivoTrue();
}
