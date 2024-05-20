package school.sptech.atividaderelacionamento.dto.projeto;


import lombok.Data;
import school.sptech.atividaderelacionamento.dto.tarefa.TarefaListagemDto;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjetoListagemDto {

    private Integer id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<TarefaListagemDto> tarefas;

    @Data
    public static class TarefaListagemDto {
        private Integer id;
        private String nome;
        private String descricao;
        private boolean concluida;
        private String prioridade;
    }

}