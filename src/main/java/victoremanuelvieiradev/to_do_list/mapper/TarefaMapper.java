package victoremanuelvieiradev.to_do_list.mapper;

import victoremanuelvieiradev.to_do_list.entity.Tarefa;
import victoremanuelvieiradev.to_do_list.web.dto.TarefaDTO;

public class TarefaMapper {
    
    public static Tarefa toTarefa(TarefaDTO dto){
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setStatus(dto.getStatus());

        return tarefa;
    }

    public static TarefaDTO toTarefaDTO(Tarefa tarefa){
        TarefaDTO dto = new TarefaDTO();
        dto.setDescricao(tarefa.getDescricao());
        dto.setId(tarefa.getId());
        dto.setStatus(tarefa.getStatus());
        dto.setDataCriacao(dto.getDataCriacao());
        dto.setTitulo(tarefa.getTitulo());

        return dto;
    }


}
