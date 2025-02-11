package victoremanuelvieiradev.to_do_list.interfaces;

import org.springframework.stereotype.Component;

import victoremanuelvieiradev.to_do_list.entity.Usuario;

@Component
public interface IJwtService {
    public String createToken(Usuario usuario);
    public String validToken(String token);
}
