package victoremanuelvieiradev.to_do_list.interfaces;

import java.util.List;

import org.springframework.stereotype.Component;

import victoremanuelvieiradev.to_do_list.entity.Tarefa;

@Component
public interface ITarefa {
    
    public Tarefa save(Tarefa tarefa);
    public Tarefa findTarefa(Long id);
    public void deleteById(Long id);
    public List<Tarefa> findAll();
    public Tarefa updateTarefa(Long id,Tarefa tarefa);
}
