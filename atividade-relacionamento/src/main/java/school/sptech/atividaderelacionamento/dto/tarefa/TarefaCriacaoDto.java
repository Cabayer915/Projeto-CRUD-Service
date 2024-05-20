package school.sptech.atividaderelacionamento.dto.tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
public class TarefaCriacaoDto {

    @NotBlank(message = "O nome não pode ser nulo, vazio ou conter espaços em branco")
    private String nome;
    @NotBlank(message = "A descrição não pode ser nula, vazia ou conter espaços em branco")
    private String descricao;
    @NotBlank(message = "A prioridade não pode ser nula, vazia ou conter espaços em branco")
    private String prioridade;
    @NotNull(message = "O projetoId não pode ser nulo")
    private Integer projetoId;
}