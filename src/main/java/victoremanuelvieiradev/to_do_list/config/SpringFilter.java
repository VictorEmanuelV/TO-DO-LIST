package victoremanuelvieiradev.to_do_list.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import victoremanuelvieiradev.to_do_list.interfaces.IJwtService;
import victoremanuelvieiradev.to_do_list.repository.UsuarioRepository;

@Configuration
@AllArgsConstructor
public class SpringFilter extends OncePerRequestFilter{
    private final IJwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            
            String token = extrairToken(request);

            if(token != null){
                String email = jwtService.validToken(token);

                var usuario = usuarioRepository.findByEmail(email);

                var auth = UsernamePasswordAuthenticationToken.authenticated(usuario,null,usuario.get().getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
            

    }

    public String extrairToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");

        if(header == null){
            return null;
        }

        if(!header.split(" ")[0].equals("Bearer")){
            return null;
        }

        return header.split(" ")[1];
    }
    
}
