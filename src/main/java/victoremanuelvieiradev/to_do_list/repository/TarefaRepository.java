package victoremanuelvieiradev.to_do_list.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import victoremanuelvieiradev.to_do_list.entity.Tarefa;
import victoremanuelvieiradev.to_do_list.entity.Usuario;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long>{
    Optional<Tarefa> findByIdAndUsuario(Long id,Usuario usuario);
    List<Tarefa> findByUsuario(Usuario usuario);
}
