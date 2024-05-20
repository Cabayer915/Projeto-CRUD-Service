package school.sptech.atividaderelacionamento.dto.tarefa;

import lombok.RequiredArgsConstructor;
import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;
import school.sptech.atividaderelacionamento.repository.projeto.ProjetoRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TarefaMapper {

    final ProjetoRepository projetoRepository;

    public static TarefaListagemDto toDto(Tarefa entity) {
        TarefaListagemDto dto = new TarefaListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setConcluida(entity.isConcluida());
        dto.setPrioridade(entity.getPrioridade());


        if(entity.getProjeto() != null){
            TarefaListagemDto.ProjetoListagemDto projetoDto = new TarefaListagemDto.ProjetoListagemDto();
            projetoDto.setId(entity.getProjeto().getId());
            projetoDto.setNome(entity.getProjeto().getNome());
            projetoDto.setDescricao(entity.getProjeto().getDescricao());
            projetoDto.setDataInicio(entity.getProjeto().getDataInicio());
            projetoDto.setDataFim(entity.getProjeto().getDataFim());
            dto.setProjeto(projetoDto);

        }
        return dto;


    }

    public static List<TarefaListagemDto> toDto(List<Tarefa> entities) {
        List<TarefaListagemDto> dtos = new ArrayList<>();
        for (Tarefa entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static Tarefa toEntity(TarefaCriacaoDto dto) {
        Tarefa entity = new Tarefa();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setConcluida(false);
        entity.setPrioridade(dto.getPrioridade());

        return entity;
    }
}