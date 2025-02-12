package victoremanuelvieiradev.to_do_list.web.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import victoremanuelvieiradev.to_do_list.enums.EnumTyp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private EnumTyp role = EnumTyp.COMUM;
}
