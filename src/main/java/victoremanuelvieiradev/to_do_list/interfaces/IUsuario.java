package victoremanuelvieiradev.to_do_list.interfaces;
import java.util.List;
import victoremanuelvieiradev.to_do_list.entity.Usuario;

public interface IUsuario {
    public Usuario save(Usuario usuario);
    public void delete(Long id);
    public Usuario findUser(Long id);
    public Usuario updateUser(Usuario usuario, String email);
    public List<Usuario> findAll();
    public Usuario findByEmail(String email);
}   
