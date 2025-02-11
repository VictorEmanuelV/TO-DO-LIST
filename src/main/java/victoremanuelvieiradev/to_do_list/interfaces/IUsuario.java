package victoremanuelvieiradev.to_do_list.interfaces;
import java.util.List;

import org.springframework.stereotype.Component;

import victoremanuelvieiradev.to_do_list.entity.Usuario;

@Component
public interface IUsuario {
    public Usuario save(Usuario usuario);
    public void delete(Long id);
    public Usuario findUser(Long id);
    public Usuario updateUser(Usuario usuario, String email);
    public List<Usuario> findAll();
    public Usuario findByEmail(String email);
}   
