package victoremanuelvieiradev.to_do_list.interfaces;

import java.util.List;
import org.springframework.stereotype.Component;
import victoremanuelvieiradev.to_do_list.web.dto.TarefaDTO;

@Component
public interface ITarefa {
    
    public TarefaDTO save(TarefaDTO dto);
    public TarefaDTO findTarefa(Long id);
    public void deleteById(Long id);
    public List<TarefaDTO> findAll();
    public TarefaDTO updateTarefa(Long id,TarefaDTO dto);
}
