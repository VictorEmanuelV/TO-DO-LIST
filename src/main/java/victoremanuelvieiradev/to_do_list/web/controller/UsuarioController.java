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
import victoremanuelvieiradev.to_do_list.interfaces.IUsuario;
import victoremanuelvieiradev.to_do_list.web.dto.UsuarioDTO;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final IUsuario iUsuario;


    @Operation(summary = "Criar um novo usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "409", description = "EMAIL ALREADY EXIST"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO dto){
        return ResponseEntity.status(201).body(iUsuario.save(dto));
    }
    @Operation(summary = "Deletar um usuario / Requer autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iUsuario.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Encontrar um usuario / Requer autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findUser(@PathVariable Long id){
        return ResponseEntity.ok().body(iUsuario.findUser(id));
    }
    @Operation(summary = "Atualizar dados do usuario / Requer autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUser(@Valid @RequestBody UsuarioDTO dto,@PathVariable Long id){
        return ResponseEntity.ok().body(iUsuario.updateUser(dto,id));
    }
    @Operation(summary = "Encontrar todos os usuarios / Requer autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "OK"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return ResponseEntity.ok().body(iUsuario.findAll());
    }
}
