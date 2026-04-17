package desafio.healthtech.care.service;

import desafio.healthtech.care.dto.consulta.ConsultaDTORequest;
import desafio.healthtech.care.dto.consulta.ConsultaDTOResponse;
import desafio.healthtech.care.dto.medico.MedicoDTOResponse;
import desafio.healthtech.care.dto.paciente.PacienteDTOResponse;
import desafio.healthtech.care.enums.ConsultaStatus;
import desafio.healthtech.care.exception.ConflictException;
import desafio.healthtech.care.exception.NotFoundException;
import desafio.healthtech.care.exception.UnprocessableEntityException;
import desafio.healthtech.care.mapper.ToResponseMapper;
import desafio.healthtech.care.model.Consulta;
import desafio.healthtech.care.model.Medico;
import desafio.healthtech.care.model.Paciente;
import desafio.healthtech.care.repository.ConsultaRepository;
import desafio.healthtech.care.repository.MedicoRepository;
import desafio.healthtech.care.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ToResponseMapper toResponseMapper;

    public List<ConsultaDTOResponse> findAll() {
        return consultaRepository.findAll()
                .stream()
                .map(toResponseMapper::consultaToDTOResponse)
                .toList();
    }

    public ConsultaDTOResponse create(ConsultaDTORequest request) {
        Paciente paciente = pacienteRepository.findById(request.pacienteId())
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(request.medicoId())
                .orElseThrow(() -> new NotFoundException("Médico não encontrado"));

        LocalDateTime dataHora = request.dataHora();

        if (!paciente.isAtivo()) {
            throw new UnprocessableEntityException("Não é possível agendar uma consulta com um paciente inativo");
        }

        if (!medico.isAtivo()) {
            throw new UnprocessableEntityException("Não é possível agendar uma consulta com um médico inativo");
        }

        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new UnprocessableEntityException("Não é possível agendar uma consulta no passado");
        }

        if (consultaRepository.existsByMedicoIdAndDataHoraAndStatus(medico.getId(), dataHora, ConsultaStatus.AGENDADA)) {
            throw new ConflictException("O médico já possui uma consulta agendada para este horário");
        }

        LocalDateTime inicioDia = dataHora.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fimDia = dataHora.withHour(23).withMinute(59).withSecond(59);

        if (consultaRepository.existsByPacienteIdAndDataHoraBetweenAndStatus(paciente.getId(), inicioDia, fimDia, ConsultaStatus.AGENDADA)) {
            throw new ConflictException("O paciente já possui uma consulta agendada para este dia");
        }

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .medico(medico)
                .dataHora(dataHora)
                .status(ConsultaStatus.AGENDADA)
                .build();

        return toResponseMapper.consultaToDTOResponse(consultaRepository.save(consulta));
    }

    public void delete(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));

        consulta.setStatus(ConsultaStatus.CANCELADA);
        consultaRepository.save(consulta);
    }
}
