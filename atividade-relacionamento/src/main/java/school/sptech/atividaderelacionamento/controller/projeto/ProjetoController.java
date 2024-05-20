package school.sptech.atividaderelacionamento.controller.projeto;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.atividaderelacionamento.dto.projeto.ProjetoCriacaoDto;
import school.sptech.atividaderelacionamento.dto.projeto.ProjetoListagemDto;
import school.sptech.atividaderelacionamento.dto.projeto.ProjetoMapper;
import school.sptech.atividaderelacionamento.entity.projeto.Projeto;
import school.sptech.atividaderelacionamento.service.projeto.ProjetoService;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    final ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<ProjetoListagemDto>> listarProjetos() {
        return service.listarProjetos().isEmpty()?
                ResponseEntity.status(204).build() :
                ResponseEntity.ok(ProjetoMapper.toDto(service.listarProjetos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoListagemDto> buscarProjeto(@PathVariable Integer id) {
        return service.buscarProjetoPorId(id).isEmpty()?
                ResponseEntity.status(404).build() :
                ResponseEntity.ok(ProjetoMapper.toDto(service.buscarProjetoPorId(id).get()));
    }

    @PostMapping
    public ResponseEntity<ProjetoListagemDto> criarProjeto(@RequestBody @Valid ProjetoCriacaoDto projetoCriacaoDto) {
        if(projetoCriacaoDto.getDataInicio().isEqual(projetoCriacaoDto.getDataFim())
                || projetoCriacaoDto.getDataInicio().isAfter(projetoCriacaoDto.getDataFim())) {
            return ResponseEntity.status(400).body(new ProjetoListagemDto());
        }

        Projeto projeto = service.salvarProjeto(ProjetoMapper.toEntity(projetoCriacaoDto));
        ProjetoListagemDto dto = ProjetoMapper.toDto(projeto);

        return ResponseEntity.status(201).body(dto);
    }
}