package desafio.healthtech.care.service;

import desafio.healthtech.care.dto.paciente.PacienteDTORequest;
import desafio.healthtech.care.dto.paciente.PacienteDTOResponse;
import desafio.healthtech.care.mapper.ToResponseMapper;
import desafio.healthtech.care.model.Paciente;
import desafio.healthtech.care.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ToResponseMapper toResponseMapper;

    public List<PacienteDTOResponse> findAll() {
        return pacienteRepository.findByAtivoTrue()
                .stream()
                .map(toResponseMapper::pacienteToDTOResponse)
                .toList();
    }

    public PacienteDTOResponse findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        return toResponseMapper.pacienteToDTOResponse(paciente);
    }

    public PacienteDTOResponse create(PacienteDTORequest request) {
        Paciente paciente = Paciente.builder()
                .nome(request.nome())
                .cpf(request.cpf())
                .email(request.email())
                .telefone(request.telefone())
                .dataNascimento(request.dataNascimento())
                .build();

        return toResponseMapper.pacienteToDTOResponse(pacienteRepository.save(paciente));
    }

    public PacienteDTOResponse update(Long id, PacienteDTORequest request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setNome(request.nome());
        paciente.setTelefone(request.telefone());

        return toResponseMapper.pacienteToDTOResponse(pacienteRepository.save(paciente));
    }

    public void delete(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setAtivo(false);

        pacienteRepository.save(paciente);
    }
}
