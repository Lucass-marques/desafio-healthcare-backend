package desafio.healthtech.care.dto.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaDTORequest(

        @NotNull(message = "O ID do paciente é obrigatório")
        Long pacienteId,

        @NotNull(message = "O ID do médico é obrigatório")
        Long medicoId,

        @NotNull(message = "O horário da consulta é obrigatório")
        @Future(message = "A consulta deve ser agendada para uma data futura")
        LocalDateTime dataHora
) {}
