package desafio.healthtech.care.controller;

import desafio.healthtech.care.dto.medico.MedicoDTORequest;
import desafio.healthtech.care.dto.medico.MedicoDTOResponse;
import desafio.healthtech.care.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoDTOResponse>> listarTodos() {
        return ResponseEntity.ok(medicoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTOResponse> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MedicoDTOResponse> criarMedico(@RequestBody MedicoDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicoDTOResponse> deletarMedico(@PathVariable Long id) {
        medicoService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
