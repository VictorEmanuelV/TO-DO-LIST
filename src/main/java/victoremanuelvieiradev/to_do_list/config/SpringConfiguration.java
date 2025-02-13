package victoremanuelvieiradev.to_do_list.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableWebMvc
@Configuration
public class SpringConfiguration {

    private final SpringFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
            return security
                  .csrf(csrf-> csrf.disable())
                  .formLogin(formLogin-> formLogin.disable())
                  .httpBasic(httpBasic->httpBasic.disable())
                  .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST,"/auth").permitAll()
                        .requestMatchers(HttpMethod.POST,"/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST,"/tarefas").hasRole("COMUM")
                        .requestMatchers(HttpMethod.DELETE,"/usuarios/{id}").hasRole("COMUM")
                        .requestMatchers(HttpMethod.GET,"/usuarios/{id}").hasRole("COMUM")
                        .requestMatchers(HttpMethod.GET,"/usuarios").hasRole("COMUM")
                        .requestMatchers(HttpMethod.PUT,"/usuarios/{email}").hasRole("COMUM")
                        .requestMatchers(HttpMethod.GET,"tarefas/{id}").hasRole("COMUM")
                        .requestMatchers(HttpMethod.PUT,"tarefas/{id}").hasRole("COMUM")
                        .requestMatchers(HttpMethod.DELETE,"tarefas/{id}").hasRole("COMUM")
                        .requestMatchers(HttpMethod.GET,"tarefas").hasRole("COMUM")
                        .anyRequest().authenticated())
                  .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
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
