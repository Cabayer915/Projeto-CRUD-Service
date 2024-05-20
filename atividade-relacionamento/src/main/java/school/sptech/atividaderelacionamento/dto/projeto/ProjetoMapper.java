package school.sptech.atividaderelacionamento.dto.projeto;

import school.sptech.atividaderelacionamento.entity.projeto.Projeto;
import school.sptech.atividaderelacionamento.entity.tarefa.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class ProjetoMapper {

    public static ProjetoListagemDto toDto(Projeto entity) {
        ProjetoListagemDto dto = new ProjetoListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFim(entity.getDataFim());

        if(entity.getTarefas() != null) {
            List<ProjetoListagemDto.TarefaListagemDto> tarefas = new ArrayList<>();
            for (Tarefa tarefa : entity.getTarefas()) {

                ProjetoListagemDto.TarefaListagemDto tarefaDto = new ProjetoListagemDto.TarefaListagemDto();
                tarefaDto.setId(tarefa.getId());
                tarefaDto.setNome(tarefa.getNome());
                tarefaDto.setDescricao(tarefa.getDescricao());
                tarefaDto.setConcluida(tarefa.isConcluida());
                tarefaDto.setPrioridade(tarefa.getPrioridade());
                tarefas.add(tarefaDto);
            }
            dto.setTarefas(tarefas);
        }
        return dto;

    }

    public static List<ProjetoListagemDto> toDto(List<Projeto> entityList) {
        List<ProjetoListagemDto> dtos = new ArrayList<>();
        for (Projeto projeto : entityList) {
            dtos.add(toDto(projeto));
        }
        return dtos;
    }

    public static Projeto toEntity(ProjetoCriacaoDto dto) {
        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataFim(dto.getDataFim());
        return projeto;
    }
}