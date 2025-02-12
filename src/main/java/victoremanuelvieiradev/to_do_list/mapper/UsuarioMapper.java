package victoremanuelvieiradev.to_do_list.mapper;

import victoremanuelvieiradev.to_do_list.entity.Usuario;
import victoremanuelvieiradev.to_do_list.web.dto.UsuarioDTO;
public class UsuarioMapper {
    

    public static Usuario toUsuario(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setRole(dto.getRole());
        usuario.setSenha(dto.getSenha());
        
        return usuario;
    }
    
    public static UsuarioDTO toUsuarioDTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmail(usuario.getEmail());
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setRole(usuario.getRole());
        dto.setSenha(usuario.getSenha());
        
        return dto;
    }
}
