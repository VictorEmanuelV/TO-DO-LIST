package victoremanuelvieiradev.to_do_list.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import victoremanuelvieiradev.to_do_list.entity.Usuario;
import victoremanuelvieiradev.to_do_list.interfaces.IUsuario;
import victoremanuelvieiradev.to_do_list.mapper.UsuarioMapper;
import victoremanuelvieiradev.to_do_list.repository.UsuarioRepository;
import victoremanuelvieiradev.to_do_list.web.dto.UsuarioDTO;

@AllArgsConstructor
@Service
public class UsuarioService implements IUsuario{
    private final PasswordEncoder encode;
    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO save(UsuarioDTO dto) {
        
        var usuario = UsuarioMapper.toUsuario(dto);
        usuario.setSenha(encode.encode(usuario.getSenha()));
        var response = usuarioRepository.save(usuario);

        return UsuarioMapper.toUsuarioDTO(response);
    }

    @Override
    public void delete(Long id) {
      usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO findUser(Long id) {
       return UsuarioMapper.toUsuarioDTO(usuarioRepository.findById(id).get());
    }

    @Override
    public UsuarioDTO updateUser(UsuarioDTO dto, String email) {
       
        var user = findByEmail(email);

         user.setEmail(dto.getEmail());
         user.setNome(dto.getNome());
         user.setSenha(encode.encode(dto.getSenha()));
   
         var response = usuarioRepository.save(user);
         return UsuarioMapper.toUsuarioDTO(response);
    }

    @Override
    public List<UsuarioDTO> findAll() {
       return usuarioRepository
            .findAll()
            .stream()
            .map(x -> UsuarioMapper.toUsuarioDTO(x)).collect(Collectors.toList());
    }

    @Override
    public Usuario findByEmail(String email){
      return usuarioRepository.findByEmail(email).orElseThrow(
        ()-> new RuntimeException("usuario nao encontrado por email"+email)
      );
    }
    
}
