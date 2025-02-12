package victoremanuelvieiradev.to_do_list.interfaces;
import java.util.List;
import org.springframework.stereotype.Component;

import victoremanuelvieiradev.to_do_list.entity.Usuario;
import victoremanuelvieiradev.to_do_list.web.dto.UsuarioDTO;

@Component
public interface IUsuario {
    public UsuarioDTO save(UsuarioDTO dto);
    public void delete(Long id);
    public UsuarioDTO findUser(Long id);
    public UsuarioDTO updateUser(UsuarioDTO dto, String email);
    public List<UsuarioDTO> findAll();
    public Usuario findByEmail(String email);
}   
