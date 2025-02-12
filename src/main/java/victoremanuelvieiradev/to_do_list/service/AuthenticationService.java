package victoremanuelvieiradev.to_do_list.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import victoremanuelvieiradev.to_do_list.interfaces.IAuthenticationService;
import victoremanuelvieiradev.to_do_list.interfaces.IJwtService;
import victoremanuelvieiradev.to_do_list.repository.UsuarioRepository;
import victoremanuelvieiradev.to_do_list.web.dto.AuthDTO;

@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService{
    private final UsuarioRepository repository;
    private final IJwtService jwt;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return repository.findByEmail(email).orElseThrow(
           () -> new RuntimeException("error")
       );

    
    }

    @Override
    public String generateToken(AuthDTO dto) {
       var usuario = repository.findByEmail(dto.getEmail()).orElseThrow(
        () -> new RuntimeException("error")
    );
        return jwt.createToken(usuario);
    }

    @Override
    public void authenticate(AuthDTO dto, AuthenticationManager manager) {
       var auth = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
       manager.authenticate(auth);
    }
    
}
