package victoremanuelvieiradev.to_do_list.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import victoremanuelvieiradev.to_do_list.interfaces.ITarefa;
import victoremanuelvieiradev.to_do_list.web.dto.TarefaDTO;

@RequestMapping("/tarefas")
@AllArgsConstructor
@RestController
public class TarefaController {
    private final ITarefa iTarefa;

    @Operation(summary = "Criar uma nova Tarefa / Requer autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "409", description = "INVALID DATA"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @PostMapping
    public ResponseEntity<TarefaDTO> create(@Valid @RequestBody TarefaDTO dto){
        return ResponseEntity.status(201).body(iTarefa.save(dto));
    }
    @Operation(summary = "Encontrar uma nova Tarefa / Requer autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> findTarefa(@PathVariable Long id){
        return ResponseEntity.ok().body(iTarefa.findTarefa(id));
    }
    @Operation(summary = "Deletar uma nova Tarefa / Requer autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id){
        iTarefa.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Buscar todas tarefas / Requer autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
    })
    @GetMapping
    public ResponseEntity<List<TarefaDTO>> findAll(){
        return ResponseEntity.ok().body(iTarefa.findAll());
    }
    @Operation(summary = "Atualizar dados de uma Tarefa / Requer autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
        @ApiResponse(responseCode = "409", description = "INVALID DATA")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> updateTarefa(@Valid @RequestBody TarefaDTO dto,@PathVariable Long id){
        return ResponseEntity.ok().body(iTarefa.updateTarefa(id, dto));
    }
}
