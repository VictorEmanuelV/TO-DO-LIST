package victoremanuelvieiradev.to_do_list.interfaces;

import victoremanuelvieiradev.to_do_list.entity.Usuario;

public interface IJwtService {
    public String createToken(Usuario usuario);
    public String validToken(String token);
}
