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
import victoremanuelvieiradev.to_do_list.entity.Usuario;
import victoremanuelvieiradev.to_do_list.interfaces.IUsuario;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final IUsuario iUsuario;


    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        return ResponseEntity.status(201).body(iUsuario.save(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iUsuario.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUser(@PathVariable Long id){
        return ResponseEntity.ok().body(iUsuario.findUser(id));
    }
    @PutMapping("/{email}")
    public ResponseEntity<Usuario> updateUser(@RequestBody Usuario usuario,@PathVariable String email){
        return ResponseEntity.ok().body(iUsuario.updateUser(usuario,email));
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok().body(iUsuario.findAll());
    }
}
