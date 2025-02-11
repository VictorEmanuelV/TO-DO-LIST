package victoremanuelvieiradev.to_do_list.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import victoremanuelvieiradev.to_do_list.entity.Usuario;
import victoremanuelvieiradev.to_do_list.interfaces.IUsuario;
import victoremanuelvieiradev.to_do_list.repository.UsuarioRepository;

@AllArgsConstructor
@Service
public class UsuarioService implements IUsuario{

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
      usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario findUser(Long id) {
       return usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario updateUser(Usuario usuario, String email) {
       var user = usuarioRepository.findByEmail(email);

       if(user.isPresent()){
         user.get().setEmail(usuario.getEmail());
         user.get().setNome(usuario.getNome());
         user.get().setSenha(usuario.getSenha());
       }
      return usuarioRepository.save(user.get());
    }

    @Override
    public List<Usuario> findAll() {
       return usuarioRepository.findAll();
    }
    
}
