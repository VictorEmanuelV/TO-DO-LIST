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
import victoremanuelvieiradev.to_do_list.interfaces.IUsuario;
import victoremanuelvieiradev.to_do_list.web.dto.UsuarioDTO;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final IUsuario iUsuario;


    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO dto){
        return ResponseEntity.status(201).body(iUsuario.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iUsuario.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findUser(@PathVariable Long id){
        return ResponseEntity.ok().body(iUsuario.findUser(id));
    }
    @PutMapping("/{email}")
    public ResponseEntity<UsuarioDTO> updateUser(@RequestBody UsuarioDTO dto,@PathVariable String email){
        return ResponseEntity.ok().body(iUsuario.updateUser(dto,email));
    }
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return ResponseEntity.ok().body(iUsuario.findAll());
    }
}
