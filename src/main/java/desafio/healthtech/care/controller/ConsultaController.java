package desafio.healthtech.care.controller;

import desafio.healthtech.care.dto.consulta.ConsultaDTORequest;
import desafio.healthtech.care.dto.consulta.ConsultaDTOResponse;
import desafio.healthtech.care.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Consultas", description = "Chamadas API relacionadas a consultas")
@RestController
@RequiredArgsConstructor
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Operation(summary = "Listar todos as consultas", description = "Lista todos os registros de consultas existentes no banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "200", description = "Listagem de consultas efetuada com sucesso")
    @GetMapping
    public ResponseEntity<List<ConsultaDTOResponse>> listarTodos() {
        return ResponseEntity.ok(consultaService.findAll());
    }

    @Operation(summary = "Criar uma consulta", description = "Cria um registro de consulta no banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "201", description = "Consulta criada com sucesso")
    @PostMapping
    public ResponseEntity<ConsultaDTOResponse> criarConsulta(@RequestBody @Valid ConsultaDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.create(request));
    }

    @Operation(summary = "Cancela a consulta", description = "Cancela (ativo = false) a consulta no banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "204", description = "Consulta cancelada com sucesso")
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ConsultaDTOResponse> cancelarConsulta(@PathVariable Long id) {
        consultaService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
