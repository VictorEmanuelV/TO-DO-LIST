package victoremanuelvieiradev.to_do_list.interfaces;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import victoremanuelvieiradev.to_do_list.web.dto.AuthDTO;

public interface IAuthenticationService extends UserDetailsService{
    public String generateToken(AuthDTO dto);
    public void authenticateToken(AuthDTO dto,AuthenticationManager manager);
}
