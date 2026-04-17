package desafio.healthtech.care.repository;

import desafio.healthtech.care.enums.ConsultaStatus;
import desafio.healthtech.care.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndDataHoraAndStatus (Long medicoId, LocalDateTime dataHora, ConsultaStatus status);

    boolean existsByPacienteIdAndDataHoraBetweenAndStatus(Long pacienteId, LocalDateTime inicioDia, LocalDateTime fimDia, ConsultaStatus status);
}
