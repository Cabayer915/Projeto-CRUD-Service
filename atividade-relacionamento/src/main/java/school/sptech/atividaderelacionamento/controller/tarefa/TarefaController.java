package school.sptech.atividaderelacionamento.controller.tarefa;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.atividaderelacionamento.dto.tarefa.TarefaCriacaoDto;
import school.sptech.atividaderelacionamento.dto.tarefa.TarefaListagemDto;
import school.sptech.atividaderelacionamento.dto.tarefa.TarefaMapper;
import school.sptech.atividaderelacionamento.entity.projeto.Projeto;
import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;
import school.sptech.atividaderelacionamento.service.tarefa.TarefaService;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TarefaListagemDto>> listarTarefas() {
        return service.listarTarefas().isEmpty() ?
                ResponseEntity.status(204).build() :
                ResponseEntity.ok(TarefaMapper.toDto(service.listarTarefas()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaListagemDto> buscarTarefa(@PathVariable Integer id) {
        return service.buscarTarefaPorId(id).isEmpty() ?
                ResponseEntity.status(404).build() :
                ResponseEntity.ok(TarefaMapper.toDto(service.buscarTarefaPorId(id).get()));
    }

    @PostMapping
    public ResponseEntity<TarefaListagemDto> criarTarefa(@RequestBody @Valid TarefaCriacaoDto tarefaCriacaoDto) {
        System.out.println(tarefaCriacaoDto);
        if(tarefaCriacaoDto.getProjetoId() == null) {
            return ResponseEntity.status(400).body(TarefaMapper.toDto(TarefaMapper.toEntity(tarefaCriacaoDto)));
        }
        Tarefa tarefa = service.salvarTarefa(TarefaMapper.toEntity(tarefaCriacaoDto), tarefaCriacaoDto.getProjetoId());
        return ResponseEntity.status(201).body(TarefaMapper.toDto(tarefa));
    }
}