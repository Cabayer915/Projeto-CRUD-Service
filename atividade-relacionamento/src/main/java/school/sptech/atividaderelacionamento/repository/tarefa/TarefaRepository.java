package school.sptech.atividaderelacionamento.repository.tarefa;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}
