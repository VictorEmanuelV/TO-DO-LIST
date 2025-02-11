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
import victoremanuelvieiradev.to_do_list.entity.Tarefa;
import victoremanuelvieiradev.to_do_list.interfaces.ITarefa;

@RequestMapping("/tarefas")
@AllArgsConstructor
@RestController
public class TarefaController {
    private final ITarefa iTarefa;

    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefa){
        return ResponseEntity.status(201).body(iTarefa.save(tarefa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> findTarefa(@PathVariable Long id){
        return ResponseEntity.ok().body(iTarefa.findTarefa(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id){
        iTarefa.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> findAll(){
        return ResponseEntity.ok().body(iTarefa.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@RequestBody Tarefa tarefa,@PathVariable Long id){
        return ResponseEntity.ok().body(iTarefa.updateTarefa(id, tarefa));
    }
}
