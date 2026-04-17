package desafio.healthtech.care.dto.consulta;


import desafio.healthtech.care.dto.medico.MedicoDTOResponse;
import desafio.healthtech.care.dto.paciente.PacienteDTOResponse;
import desafio.healthtech.care.enums.ConsultaStatus;
import desafio.healthtech.care.model.Medico;
import desafio.healthtech.care.model.Paciente;

import java.time.LocalDateTime;

public record ConsultaDTOResponse(
        Long id,
        PacienteDTOResponse pacienteId,
        MedicoDTOResponse medicoId,
        LocalDateTime dataHora,
        ConsultaStatus status
) {}
