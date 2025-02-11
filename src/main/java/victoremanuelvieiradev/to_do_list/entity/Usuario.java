package victoremanuelvieiradev.to_do_list.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import victoremanuelvieiradev.to_do_list.enums.EnumTyp;

@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name="nome")
    private String nome;
    @Column(name="email")
    private String email;
    @Column(name="senha")
    private String senha;
    @Enumerated(EnumType.STRING)
    private EnumTyp role = EnumTyp.COMUM;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role == EnumTyp.ADM){
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADM"),
                new SimpleGrantedAuthority("ROLE_COMUM")
            );
        }
        return List.of(
            new SimpleGrantedAuthority("ROLE_COMUM")
        );
    }
    @Override
    public String getPassword() {
        return senha;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
