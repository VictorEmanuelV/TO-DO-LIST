package victoremanuelvieiradev.to_do_list.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import victoremanuelvieiradev.to_do_list.entity.Usuario;
import victoremanuelvieiradev.to_do_list.interfaces.IJwtService;

@Service
public class JwtService implements IJwtService{
     @Override
    public String createToken(Usuario usuario) {
        try {
            return JWT.create()
                    .withIssuer("api-rest-spring-security")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dateExpire())
                    .sign(algorithm());
        }catch (JWTCreationException ex){
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public String validToken(String token) {
        try {
            return JWT.require(algorithm())
                    .withIssuer("api-rest-spring-security")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
    private Algorithm algorithm(){
        return Algorithm.HMAC256("my-secret");
    }
    private Instant dateExpire(){
        return LocalDateTime.now().plusMinutes(2)
                .atZone(ZoneOffset.of("-03:00"))
                .toInstant();
    }
}
