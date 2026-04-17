package desafio.healthtech.care.controller;

import desafio.healthtech.care.dto.consulta.ConsultaDTORequest;
import desafio.healthtech.care.dto.consulta.ConsultaDTOResponse;
import desafio.healthtech.care.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<ConsultaDTOResponse>> listarTodos() {
        return ResponseEntity.ok(consultaService.findAll());
    }

    @PostMapping
    public ResponseEntity<ConsultaDTOResponse> criarConsulta(@RequestBody ConsultaDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.create(request));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaDTOResponse> cancelarConsulta(@PathVariable Long id) {
        consultaService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
