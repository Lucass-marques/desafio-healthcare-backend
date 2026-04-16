package desafio.healthtech.care.dto.medico;

import desafio.healthtech.care.enums.EspecialidadeEnum;

import java.time.LocalDate;

public record MedicoDTOResponse(
        Long id,
        String nome,
        String crm,
        String email,
        EspecialidadeEnum especialidade,
        boolean ativo
) {}
