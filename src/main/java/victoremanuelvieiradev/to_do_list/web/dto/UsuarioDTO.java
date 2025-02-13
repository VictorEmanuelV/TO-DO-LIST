package victoremanuelvieiradev.to_do_list.web.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String nome;
    @Email
    private String email;
    @NotBlank
    private String senha;
    @Enumerated(EnumType.STRING)
    private EnumTyp role = EnumTyp.COMUM;
}
