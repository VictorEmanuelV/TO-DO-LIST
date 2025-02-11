package victoremanuelvieiradev.to_do_list.service;
import java.util.List;

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
       
       UserDetails userDetails = (UserDetails) 
       SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       var usuario = iUsuario.findByEmail(userDetails.getUsername());
       tarefa.setUsuario(usuario);
       return repository.save(tarefa);
    }

    @Override
    public Tarefa findTarefa(Long id) {
        UserDetails userDetails = (UserDetails) 
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var usuario = iUsuario.findByEmail(userDetails.getUsername());
       return repository.findByIdAndUsuario(id,usuario).orElseThrow(
        () -> new RuntimeException("Tarefa não encontrada")
       );
      
    }

    @Override
    public void deleteById(Long id) {
        UserDetails userDetails = (UserDetails) 
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var usuario = iUsuario.findByEmail(userDetails.getUsername());
        
        var tarefa = repository.findByIdAndUsuario(id,usuario).orElseThrow(
        () -> new RuntimeException("Tarefa não encontrada")
        );

        repository.deleteById(tarefa.getId());
    }

    @Override
    public List<Tarefa> findAll() {
        UserDetails userDetails = (UserDetails) 
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var usuario = iUsuario.findByEmail(userDetails.getUsername());
        return repository.findByUsuario(usuario);
    }

    @Override
    public Tarefa updateTarefa(Long id,Tarefa tarefa) {
        UserDetails userDetails = (UserDetails) 
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var usuario = iUsuario.findByEmail(userDetails.getUsername());

        var taref = repository.findByIdAndUsuario(id, usuario).orElseThrow(
            () -> new RuntimeException("Tarefa não encontrada")
        );
        
        taref.setTitulo(tarefa.getTitulo());
        taref.setStatus(tarefa.getStatus());
        taref.setDescriçao(tarefa.getDescriçao());

        return repository.save(taref);
    }
    
}
