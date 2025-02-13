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
import victoremanuelvieiradev.to_do_list.web.exception.EmailAlreadyExistException;
import victoremanuelvieiradev.to_do_list.web.exception.UserNotFoundException;

@AllArgsConstructor
@Service
public class UsuarioService implements IUsuario{
    private final PasswordEncoder encode;
    private final UsuarioRepository usuarioRepository;
  

    @Override
    public UsuarioDTO save(UsuarioDTO dto) {
        
        var usuarioExist = usuarioRepository.findByEmail(dto.getEmail());

        if(usuarioExist.isPresent()){
           throw new EmailAlreadyExistException("Email Already Exist");
        }

        var usuario = UsuarioMapper.toUsuario(dto);
        usuario.setSenha(encode.encode(usuario.getSenha()));
        var response = usuarioRepository.save(usuario);

        return UsuarioMapper.toUsuarioDTO(response);
    }

    @Override
    public void delete(Long id) {
      var usuarioExist = usuarioRepository.findById(id);

      if(!usuarioExist.isPresent()){
        throw new UserNotFoundException("User not found: "+id);
      }
     
      usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO findUser(Long id) {
       return UsuarioMapper.toUsuarioDTO(usuarioRepository.findById(id).orElseThrow(
          ()-> new UserNotFoundException("User not found: "+id))
       );

    }

    @Override
    public UsuarioDTO updateUser(UsuarioDTO dto, Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(
          ()-> new UserNotFoundException("User not found")
        );        
       
        var user = usuarioRepository.findByEmail(dto.getEmail());
        
        if(user.isPresent()){
           if(!user.get().getId().equals(id)){
            throw new EmailAlreadyExistException("Email Already Exist");
           }
        }
        
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setSenha(encode.encode(dto.getSenha()));
        
        var response = usuarioRepository.save(usuario);

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
        ()-> new UserNotFoundException("User not found: "+email)
      );
    }
    
}
