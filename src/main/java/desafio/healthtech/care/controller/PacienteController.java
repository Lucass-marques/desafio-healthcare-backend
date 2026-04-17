package desafio.healthtech.care.controller;

import desafio.healthtech.care.dto.paciente.PacienteDTORequest;
import desafio.healthtech.care.dto.paciente.PacienteDTOResponse;
import desafio.healthtech.care.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDTOResponse>> listarTodos() {
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PacienteDTOResponse> criarPaciente(@RequestBody @Valid PacienteDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> atualizarPaciente(@PathVariable Long id, @RequestBody @Valid PacienteDTORequest request) {
        return ResponseEntity.ok(pacienteService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> deletarPaciente(@PathVariable Long id) {
        pacienteService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}