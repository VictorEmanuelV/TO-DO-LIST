package victoremanuelvieiradev.to_do_list.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import victoremanuelvieiradev.to_do_list.interfaces.IAuthenticationService;
import victoremanuelvieiradev.to_do_list.web.dto.AuthDTO;

@RequestMapping("/auth")
@AllArgsConstructor
@RestController
public class AuthenticationController {
    private final IAuthenticationService authenticationService;
    private final AuthenticationManager manager;
    
    @PostMapping
    public ResponseEntity<String> auth(@RequestBody AuthDTO dto){
       authenticationService.authenticate(dto,manager);
       String token = authenticationService.generateToken(dto);
       return ResponseEntity.ok().body(token);

    }
}
