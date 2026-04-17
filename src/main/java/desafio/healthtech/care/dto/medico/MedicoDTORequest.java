package desafio.healthtech.care.dto.medico;

import desafio.healthtech.care.enums.EspecialidadeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record MedicoDTORequest(

        @Schema(example = "Exemplo Exemplo")
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Schema(example = "12345")
        @NotBlank(message = "O CRM é obrigatório")
        @Pattern(regexp = "^\\d{4,6}$", message = "O CRM deve conter apenas números, entre 4 e 6 dígitos")
        String crm,

        @Schema(example = "exemplo@gmail.com")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Insira um e-mail válido")
        String email,

        @Schema(example = "EXEMPLO")
        @NotBlank(message = "A especialidade é obrigatória")
        EspecialidadeEnum especialidade
) {}
