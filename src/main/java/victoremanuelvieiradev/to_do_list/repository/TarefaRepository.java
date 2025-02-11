package victoremanuelvieiradev.to_do_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import victoremanuelvieiradev.to_do_list.entity.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long>{

    
}
