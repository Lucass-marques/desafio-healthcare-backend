package desafio.healthtech.care.controller;

import desafio.healthtech.care.dto.paciente.PacienteDTORequest;
import desafio.healthtech.care.dto.paciente.PacienteDTOResponse;
import desafio.healthtech.care.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pacientes", description = "Chamadas API relacionadas a pacientes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Operation(summary = "Listar todos os pacientes", description = "Lista todos os registros de pacientes existentes no banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "200", description = "Listagem de pacientes efetuada com sucesso")
    @GetMapping
    public ResponseEntity<List<PacienteDTOResponse>> listarTodos() {
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @Operation(summary = "Buscar paciente por ID", description = "Busca o registro do paciente no banco de dados passando seu ID")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "200", description = "Listagem de pacientes por ID efetuada com sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.findById(id));
    }

    @Operation(summary = "Criar um paciente", description = "Cria um registro de paciente no banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "201", description = "Criação de paciente efetuada com sucesso")
    @PostMapping
    public ResponseEntity<PacienteDTOResponse> criarPaciente(@RequestBody @Valid PacienteDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.create(request));
    }

    @Operation(summary = "Editar informações do paciente", description = "Edita um registro de paciente no banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "200", description = "Atualização de dados de paciente efetuada com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> atualizarPaciente(@PathVariable Long id, @RequestBody @Valid PacienteDTORequest request) {
        return ResponseEntity.ok(pacienteService.update(id, request));
    }

    @Operation(summary = "Deleta o paciente", description = "Deleta (ativo = false) o paciente do banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "204", description = "Exclusão de paciente efetuada com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> deletarPaciente(@PathVariable Long id) {
        pacienteService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}