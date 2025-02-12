package victoremanuelvieiradev.to_do_list.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import victoremanuelvieiradev.to_do_list.enums.EnumType;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {
    
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private EnumType status;
}
