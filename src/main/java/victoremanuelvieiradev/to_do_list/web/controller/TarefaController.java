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

import lombok.AllArgsConstructor;
import victoremanuelvieiradev.to_do_list.interfaces.ITarefa;
import victoremanuelvieiradev.to_do_list.web.dto.TarefaDTO;

@RequestMapping("/tarefas")
@AllArgsConstructor
@RestController
public class TarefaController {
    private final ITarefa iTarefa;

    @PostMapping
    public ResponseEntity<TarefaDTO> create(@RequestBody TarefaDTO dto){
        return ResponseEntity.status(201).body(iTarefa.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> findTarefa(@PathVariable Long id){
        return ResponseEntity.ok().body(iTarefa.findTarefa(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id){
        iTarefa.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> findAll(){
        return ResponseEntity.ok().body(iTarefa.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> updateTarefa(@RequestBody TarefaDTO dto,@PathVariable Long id){
        return ResponseEntity.ok().body(iTarefa.updateTarefa(id, dto));
    }
}
