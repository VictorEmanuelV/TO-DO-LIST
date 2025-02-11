package victoremanuelvieiradev.to_do_list.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import victoremanuelvieiradev.to_do_list.enums.EnumType;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_TAREFA")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descricao")
    private String descriçao;
    @Column(name = "dataCriacao")
    private LocalDateTime dataCriacao;
    @Column(name = "status")
    private EnumType status;

}
