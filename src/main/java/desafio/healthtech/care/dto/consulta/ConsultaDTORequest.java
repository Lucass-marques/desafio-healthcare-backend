package desafio.healthtech.care.dto.consulta;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaDTORequest(

        @Schema(example = "1")
        @NotNull(message = "O ID do paciente é obrigatório")
        Long pacienteId,

        @Schema(example = "1")
        @NotNull(message = "O ID do médico é obrigatório")
        Long medicoId,

        @Schema(example = "1969-01-01T00:00:00", description = "A consulta deve ser agendada para uma data futura")
        @NotNull(message = "O horário da consulta é obrigatório")
        @Future(message = "A consulta deve ser agendada para uma data futura")
        LocalDateTime dataHora
) {}
