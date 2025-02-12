package victoremanuelvieiradev.to_do_list.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import victoremanuelvieiradev.to_do_list.entity.Tarefa;

import victoremanuelvieiradev.to_do_list.interfaces.ITarefa;
import victoremanuelvieiradev.to_do_list.interfaces.IUsuario;
import victoremanuelvieiradev.to_do_list.repository.TarefaRepository;

@AllArgsConstructor
@Service
public class TarefaService implements ITarefa{
    private final TarefaRepository repository;
    private final IUsuario iUsuario;
    
    @Override
    public Tarefa save(Tarefa tarefa) {
        LocalDateTime now = LocalDateTime.now();
        var userDetails = contextHolder();

       var usuario = iUsuario.findByEmail(userDetails.getUsername());
       tarefa.setUsuario(usuario);
       tarefa.setDataCriacao(now);
       return repository.save(tarefa);
    }

    @Override
    public Tarefa findTarefa(Long id) {
        var userDetails = contextHolder();

        var usuario = iUsuario.findByEmail(userDetails.getUsername());
       return repository.findByIdAndUsuario(id,usuario).orElseThrow(
        () -> new RuntimeException("Tarefa não encontrada")
       );
      
    }

    @Override
    public void deleteById(Long id) {
        var userDetails = contextHolder();
        
        var usuario = iUsuario.findByEmail(userDetails.getUsername());
        
        var tarefa = repository.findByIdAndUsuario(id,usuario).orElseThrow(
        () -> new RuntimeException("Tarefa não encontrada")
        );

        repository.deleteById(tarefa.getId());
    }

    @Override
    public List<Tarefa> findAll() {
        var userDetails = contextHolder();

        var usuario = iUsuario.findByEmail(userDetails.getUsername());
        return repository.findByUsuario(usuario);
    }

    @Override
    public Tarefa updateTarefa(Long id,Tarefa tarefa) {
        var userDetails = contextHolder();        


        var usuario = iUsuario.findByEmail(userDetails.getUsername());

        var taref = repository.findByIdAndUsuario(id, usuario).orElseThrow(
            () -> new RuntimeException("Tarefa não encontrada")
        );
        
        taref.setTitulo(tarefa.getTitulo());
        taref.setStatus(tarefa.getStatus());
        taref.setDescricao(tarefa.getDescricao());

        return repository.save(taref);
    }
    
    private UserDetails contextHolder(){
        Optional<UserDetails> optionalUserDetails = 
            (Optional<UserDetails>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (optionalUserDetails.isPresent()) {
           UserDetails userDetails = optionalUserDetails.get();
           return userDetails;
        } else {
            throw new RuntimeException("Usuário não autenticado");
        }
    }
}
