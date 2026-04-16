package desafio.healthtech.care.dto.medico;

import desafio.healthtech.care.enums.EspecialidadeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record MedicoDTORequest(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "^\\d{4,6}$", message = "O CRM deve conter apenas números, entre 4 e 6 dígitos")
        String crm,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Insira um e-mail válido")
        String email,

        @NotBlank(message = "A especialidade é obrigatória")
        EspecialidadeEnum especialidade
) {}
