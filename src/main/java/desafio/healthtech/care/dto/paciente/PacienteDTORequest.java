package desafio.healthtech.care.dto.paciente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PacienteDTORequest(

        @Schema(example = "Exemplo Exemplo")
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Schema(example = "12345678901")
        @NotBlank(message = "O CPF é obrigatório")
        @CPF(message = "CPF inválido")
        String cpf,

        @Schema(example = "exemplo@gmail.com")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Insira um e-mail válido")
        String email,

        String telefone,

        @Schema(example = "1969-01-01")
        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNascimento
) {}
