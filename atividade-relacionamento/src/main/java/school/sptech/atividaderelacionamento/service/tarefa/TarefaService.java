package school.sptech.atividaderelacionamento.service.tarefa;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.atividaderelacionamento.entity.projeto.Projeto;
import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;
import school.sptech.atividaderelacionamento.repository.tarefa.TarefaRepository;
import school.sptech.atividaderelacionamento.service.projeto.ProjetoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    final TarefaRepository repository;
    final ProjetoService projetoService;

    public List<Tarefa> listarTarefas() {
        return repository.findAll();
    }

    public Optional<Tarefa> buscarTarefaPorId(Integer id) {
        return repository.findById(id);
    }

    public Tarefa salvarTarefa(Tarefa tarefa, Integer idProjeto) {
        if(projetoService.buscarProjetoPorId(idProjeto).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado");
        }

        tarefa.setProjeto(projetoService.buscarProjetoPorId(idProjeto).get());
        return repository.save(tarefa);
    }
}