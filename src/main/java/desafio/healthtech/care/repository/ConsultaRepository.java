package desafio.healthtech.care.repository;

import desafio.healthtech.care.enums.ConsultaStatus;
import desafio.healthtech.care.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndDataAndStatus (Long medicoId, LocalDateTime dataHora, ConsultaStatus status);

    boolean existsByPacienteIdAndStatusAndDataHoraBetween(Long pacienteId, LocalDateTime dataHora, ConsultaStatus status);
}
