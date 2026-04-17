package desafio.healthtech.care.mapper;

import desafio.healthtech.care.dto.consulta.ConsultaDTOResponse;
import desafio.healthtech.care.dto.medico.MedicoDTOResponse;
import desafio.healthtech.care.dto.paciente.PacienteDTOResponse;
import desafio.healthtech.care.model.Consulta;
import desafio.healthtech.care.model.Medico;
import desafio.healthtech.care.model.Paciente;
import org.springframework.stereotype.Component;

@Component
public class ToResponseMapper {

    public ConsultaDTOResponse consultaToDTOResponse (Consulta consulta) {
        return new ConsultaDTOResponse(
                consulta.getId(),
                pacienteToDTOResponse(consulta.getPaciente()),
                medicoToDTOResponse(consulta.getMedico()),
                consulta.getDataHora(),
                consulta.getStatus()
        );
    }

    public MedicoDTOResponse medicoToDTOResponse(Medico medico) {
        return new MedicoDTOResponse(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEmail(),
                medico.getEspecialidade(),
                medico.isAtivo()
        );
    }

    public PacienteDTOResponse pacienteToDTOResponse(Paciente paciente) {
        return new PacienteDTOResponse(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getDataNascimento(),
                paciente.isAtivo()
        );
    }
}
