package school.sptech.atividaderelacionamento.service.projeto;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.atividaderelacionamento.entity.projeto.Projeto;
import school.sptech.atividaderelacionamento.repository.projeto.ProjetoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    final ProjetoRepository repository;



    public List<Projeto> listarProjetos() {
        return repository.findAll();
    }

    public Optional<Projeto> buscarProjetoPorId(Integer id) {
        return repository.findById(id);

    }

    public Projeto salvarProjeto(Projeto projeto) {
        return repository.save(projeto);
    }
}