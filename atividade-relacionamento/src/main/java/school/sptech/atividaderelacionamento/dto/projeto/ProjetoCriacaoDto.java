package school.sptech.atividaderelacionamento.dto.projeto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjetoCriacaoDto {

    @NotBlank(message = "O nome não pode ser nulo, vazio ou conter espaços em branco")
    private String nome;
    @NotBlank(message = "A descrição não pode ser nula, vazia ou conter espaços em branco")
    private  String descricao;
    @FutureOrPresent(message = "A data de início deve ser presente ou futura")
    @NotNull(message = "A data de início não pode ser nula")
    private LocalDate dataInicio;
    @Future(message = "A data de fim deve ser futura")
    @NotNull(message = "A data de fim não pode ser nula")
    private LocalDate dataFim;

}