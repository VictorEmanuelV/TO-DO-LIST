package victoremanuelvieiradev.to_do_list.interfaces;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import victoremanuelvieiradev.to_do_list.web.dto.AuthDTO;

@Component
public interface IAuthenticationService extends UserDetailsService{
    public String generateToken(AuthDTO dto);
    public void authenticate(AuthDTO dto,AuthenticationManager manager);
}
