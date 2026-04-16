package desafio.healthtech.care.dto.paciente;

import java.time.LocalDate;

public record PacienteDTOResponse(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate dataNascimento,
        boolean ativo
) {}
