package school.sptech.atividaderelacionamento.controller.projeto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Testes para ProjetoController")
class ProjetoControllerTest {

    private static final String URL = "/projetos";
    private static final String URL_ID = "/projetos/{id}";

    @Nested
    @DisplayName("1. Cadastro")
    public class CadastroTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("1.1. Deve criar um projeto com sucesso")
        void deveCriarProjetoComSucesso() throws Exception {
            var body = """
                    {
                       "nome": "Projeto ABC",
                       "descricao": "Isso é uma descrição",
                       "dataInicio": "%s",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now(), LocalDate.now().plusDays(10));

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.nome").value("Projeto ABC"))
                    .andExpect(jsonPath("$.descricao").value("Isso é uma descrição"))
                    .andExpect(jsonPath("$.dataInicio").value(LocalDate.now().toString()))
                    .andExpect(jsonPath("$.dataFim").value(LocalDate.now().plusDays(10).toString()));
        }

        @Test
        @DisplayName("1.2. Deve retornar 400 ao tentar criar um projeto com data de início maior que a data de fim")
        void deveRetornar400AoTentarCriarProjetoComDataInicioMaiorQueDataFim() throws Exception {
            var body = """
                    {
                       "nome": "Projeto ABC",
                       "descricao": "Isso é uma descrição",
                       "dataInicio": "%s",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now().plusDays(10), LocalDate.now().plusDays(5));

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("1.3. Deve retornar 400 ao tentar criar um projeto com data de início igual a data de fim")
        void deveRetornar400AoTentarCriarProjetoComDataInicioIgualDataFim() throws Exception {
            var body = """
                    {
                       "nome": "Projeto ABC",
                       "descricao": "Isso é uma descrição",
                       "dataInicio": "%s",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now(), LocalDate.now());

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("1.4. Deve retornar 400 ao tentar criar um projeto com data de início nula")
        void deveRetornar400AoTentarCriarProjetoComDataInicioNula() throws Exception {
            var body = """
                    {
                       "nome": "Projeto ABC",
                       "descricao": "Isso é uma descrição",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now().plusDays(10));

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("1.5. Deve retornar 400 ao tentar criar um projeto com data de fim nula")
        void deveRetornar400AoTentarCriarProjetoComDataFimNula() throws Exception {
            var body = """
                    {
                       "nome": "Projeto ABC",
                       "descricao": "Isso é uma descrição",
                       "dataInicio": "%s"
                     }
                    """.formatted(LocalDate.now());

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("1.6. Deve retornar 400 ao tentar criar um projeto com nome nulo")
        void deveRetornar400AoTentarCriarProjetoComNomeNulo() throws Exception {
            var body = """
                    {
                       "descricao": "Isso é uma descrição",
                       "dataInicio": "%s",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now(), LocalDate.now().plusDays(10));

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("1.7. Deve retornar 400 ao tentar criar um projeto com descrição nula")
        void deveRetornar400AoTentarCriarProjetoComDescricaoNula() throws Exception {
            var body = """
                    {
                       "nome": "Projeto ABC",
                       "dataInicio": "%s",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now(), LocalDate.now().plusDays(10));

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("1.8. Deve retornar 400 ao tentar criar um projeto com nome vazio")
        void deveRetornar400AoTentarCriarProjetoComNomeVazio() throws Exception {
            var body = """
                    {
                       "nome": "",
                       "descricao": "Isso é uma descrição",
                       "dataInicio": "%s",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now(), LocalDate.now().plusDays(10));

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("1.9. Deve retornar 400 ao tentar criar um projeto com descrição vazia")
        void deveRetornar400AoTentarCriarProjetoComDescricaoVazia() throws Exception {
            var body = """
                    {
                       "nome": "Projeto ABC",
                       "descricao": "",
                       "dataInicio": "%s",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now(), LocalDate.now().plusDays(10));

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("1.10. Deve retornar 400 ao tentar criar um projeto com nome somente com espaços")
        void deveRetornar400AoTentarCriarProjetoComNomeSomenteComEspacos() throws Exception {
            var body = """
                    {
                       "nome": "   ",
                       "descricao": "Isso é uma descrição",
                       "dataInicio": "%s",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now(), LocalDate.now().plusDays(10));

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("1.11. Deve retornar 400 ao tentar criar um projeto com descrição somente com espaços")
        void deveRetornar400AoTentarCriarProjetoComDescricaoSomenteComEspacos() throws Exception {
            var body = """
                    {
                       "nome": "Projeto ABC",
                       "descricao": "   ",
                       "dataInicio": "%s",
                       "dataFim": "%s"
                     }
                    """.formatted(LocalDate.now(), LocalDate.now().plusDays(10));

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("2. Listagem")
    public class ListagemTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("2.1. Deve retornar 204 ao tentar listar projetos sem nenhum projeto cadastrado")
        void deveRetornar204AoTentarListarProjetosSemNenhumProjetoCadastrado() throws Exception {
            mockMvc.perform(get(URL)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("2.2. Deve retornar 200 e vetor com tamanho 5")
        void deveRetornar200EVetorComTamanho5() throws Exception {
            mockMvc.perform(get(URL)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(5))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].nome").value("Desenvolvimento Web"))
                    .andExpect(jsonPath("$[0].descricao").value("Criação de um novo site corporativo."))
                    .andExpect(jsonPath("$[0].dataInicio").value("2023-01-10"))
                    .andExpect(jsonPath("$[0].dataFim").value("2023-06-15"))
                    .andExpect(jsonPath("$[0].tarefas.length()").value(2))
                    .andExpect(jsonPath("$[0].tarefas[0].id").value(1))
                    .andExpect(jsonPath("$[0].tarefas[0].nome").value("Design do site"))
                    .andExpect(jsonPath("$[0].tarefas[0].descricao").value("Design da interface do usuário para o novo site."))
                    .andExpect(jsonPath("$[0].tarefas[0].concluida").value(false))
                    .andExpect(jsonPath("$[0].tarefas[0].prioridade").value("Alta"))
                    .andExpect(jsonPath("$[0].tarefas[1].id").value(2))
                    .andExpect(jsonPath("$[0].tarefas[1].nome").value("Codificação do Front-end"))
                    .andExpect(jsonPath("$[0].tarefas[1].descricao").value("Implementação do front-end em React."))
                    .andExpect(jsonPath("$[0].tarefas[1].concluida").value(true))
                    .andExpect(jsonPath("$[0].tarefas[1].prioridade").value("Alta"));
        }
    }

    @Nested
    @DisplayName("3. Busca por ID")
    public class BuscaTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("3.0. Deve retornar 404 ao tentar buscar um projeto sem nenhum projeto cadastrado")
        void deveRetornar204AoTentarBuscarProjetoSemNenhumProjetoCadastrado() throws Exception {
            mockMvc.perform(get(URL_ID, 1)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("3.1. Deve retornar 404 ao tentar buscar um projeto inexistente")
        void deveRetornar404AoTentarBuscarProjetoInexistente() throws Exception {
            mockMvc.perform(get(URL_ID, 42)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("3.2. Deve retornar 200 ao buscar um projeto existente")
        void deveRetornar200AoBuscarProjetoExistente() throws Exception {
            mockMvc.perform(get(URL_ID, 1)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.nome").value("Desenvolvimento Web"))
                    .andExpect(jsonPath("$.descricao").value("Criação de um novo site corporativo."))
                    .andExpect(jsonPath("$.dataInicio").value("2023-01-10"))
                    .andExpect(jsonPath("$.dataFim").value("2023-06-15"))
                    .andExpect(jsonPath("$.tarefas.length()").value(2))
                    .andExpect(jsonPath("$.tarefas[0].id").value(1))
                    .andExpect(jsonPath("$.tarefas[0].nome").value("Design do site"))
                    .andExpect(jsonPath("$.tarefas[0].descricao").value("Design da interface do usuário para o novo site."))
                    .andExpect(jsonPath("$.tarefas[0].concluida").value(false))
                    .andExpect(jsonPath("$.tarefas[0].prioridade").value("Alta"))
                    .andExpect(jsonPath("$.tarefas[1].id").value(2))
                    .andExpect(jsonPath("$.tarefas[1].nome").value("Codificação do Front-end"))
                    .andExpect(jsonPath("$.tarefas[1].descricao").value("Implementação do front-end em React."))
                    .andExpect(jsonPath("$.tarefas[1].concluida").value(true))
                    .andExpect(jsonPath("$.tarefas[1].prioridade").value("Alta"));
        }
    }
}