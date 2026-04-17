package desafio.healthtech.care.service;

import desafio.healthtech.care.dto.medico.MedicoDTORequest;
import desafio.healthtech.care.dto.medico.MedicoDTOResponse;
import desafio.healthtech.care.mapper.ToResponseMapper;
import desafio.healthtech.care.model.Medico;
import desafio.healthtech.care.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final ToResponseMapper toResponseMapper;

    public List<MedicoDTOResponse> findAll() {
        return medicoRepository.findByAtivoTrue()
                .stream()
                .map(toResponseMapper::medicoToDTOResponse)
                .toList();
    }

    public MedicoDTOResponse findById(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        return toResponseMapper.medicoToDTOResponse(medico);
    }

    public MedicoDTOResponse create(MedicoDTORequest request) {
        Medico medico = Medico.builder()
                .nome(request.nome())
                .crm(request.crm())
                .email(request.email())
                .especialidade(request.especialidade())
                .build();

        return toResponseMapper.medicoToDTOResponse(medicoRepository.save(medico));
    }

    public void delete(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        medico.setAtivo(false);

        medicoRepository.save(medico);
    }
}
