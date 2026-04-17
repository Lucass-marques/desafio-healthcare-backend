package desafio.healthtech.care.controller;

import desafio.healthtech.care.dto.medico.MedicoDTORequest;
import desafio.healthtech.care.dto.medico.MedicoDTOResponse;
import desafio.healthtech.care.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Médicos", description = "Chamadas API relacionadas a médicos")
@RestController
@RequiredArgsConstructor
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @Operation(summary = "Listar todos os médicos", description = "Lista todos os registros de médicos existentes no banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "200", description = "Listagem de médicos efetuada com sucesso")
    @GetMapping
    public ResponseEntity<List<MedicoDTOResponse>> listarTodos() {
        return ResponseEntity.ok(medicoService.findAll());
    }

    @Operation(summary = "Buscar médico por ID", description = "Busca o registro do médico no banco de dados passando seu ID")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "200", description = "Listagem de médicos por ID efetuada com sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTOResponse> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.findById(id));
    }

    @Operation(summary = "Criar um médico", description = "Cria um registro de médico no banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "201", description = "Criação de médico efetuada com sucesso")
    @PostMapping
    public ResponseEntity<MedicoDTOResponse> criarMedico(@RequestBody @Valid MedicoDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.create(request));
    }

    @Operation(summary = "Deleta o médico", description = "Deleta (ativo = false) o médico do banco de dados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "204", description = "Exclusão de médico efetuada com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<MedicoDTOResponse> deletarMedico(@PathVariable Long id) {
        medicoService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
