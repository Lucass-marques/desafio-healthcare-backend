package desafio.healthtech.care.repository;

import desafio.healthtech.care.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {}
