package victoremanuelvieiradev.to_do_list.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class SpringConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
            return security
                  .csrf(csrf-> csrf.disable())
                  .formLogin(formLogin-> formLogin.disable())
                  .httpBasic(httpBasic->httpBasic.disable())
                  .authorizeHttpRequests(auth->auth.anyRequest().permitAll()
                  ).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .build();

    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
