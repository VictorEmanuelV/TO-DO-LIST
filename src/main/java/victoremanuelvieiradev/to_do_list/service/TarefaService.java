package victoremanuelvieiradev.to_do_list.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import victoremanuelvieiradev.to_do_list.entity.Tarefa;

import victoremanuelvieiradev.to_do_list.interfaces.ITarefa;
import victoremanuelvieiradev.to_do_list.interfaces.IUsuario;
import victoremanuelvieiradev.to_do_list.mapper.TarefaMapper;
import victoremanuelvieiradev.to_do_list.repository.TarefaRepository;
import victoremanuelvieiradev.to_do_list.web.dto.TarefaDTO;

@AllArgsConstructor
@Service
public class TarefaService implements ITarefa{
    private final TarefaRepository repository;
    private final IUsuario iUsuario;
    
    @Override
    public TarefaDTO save(TarefaDTO dto) {
        LocalDateTime now = LocalDateTime.now();
        var userDetails = contextHolder();

        var usuario = iUsuario.findByEmail(userDetails.getUsername());

        var tarefa = TarefaMapper.toTarefa(dto);
        tarefa.setUsuario(usuario);
        tarefa.setDataCriacao(now);
        
        var response =  repository.save(tarefa);

        return TarefaMapper.toTarefaDTO(response);
    }

    @Override
    public TarefaDTO findTarefa(Long id) {
        var userDetails = contextHolder();

        var usuario = iUsuario.findByEmail(userDetails.getUsername());

        var response = repository.findByIdAndUsuario(id,usuario).orElseThrow(
        () -> new RuntimeException("Tarefa não encontrada")
        );
        
        return TarefaMapper.toTarefaDTO(response);
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
    public List<TarefaDTO> findAll() {
        var userDetails = contextHolder();

        var usuario = iUsuario.findByEmail(userDetails.getUsername());

        List<Tarefa> tarefas = repository.findByUsuario(usuario);

        return tarefas.stream().map(x -> TarefaMapper.toTarefaDTO(x)).collect(Collectors.toList());
    }

    @Override
    public TarefaDTO updateTarefa(Long id,TarefaDTO dto) {
        var userDetails = contextHolder();        

        var usuario = iUsuario.findByEmail(userDetails.getUsername());

        var taref = repository.findByIdAndUsuario(id, usuario).orElseThrow(
            () -> new RuntimeException("Tarefa não encontrada")
        );
        
        taref.setTitulo(dto.getTitulo());
        taref.setStatus(dto.getStatus());
        taref.setDescricao(dto.getDescricao());

        var response = repository.save(taref);

        return TarefaMapper.toTarefaDTO(response);
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
