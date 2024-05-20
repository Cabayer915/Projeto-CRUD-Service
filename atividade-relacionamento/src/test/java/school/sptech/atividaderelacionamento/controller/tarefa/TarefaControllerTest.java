package school.sptech.atividaderelacionamento.controller.tarefa;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Testes para TarefaController")
class TarefaControllerTest {

    private static final String URL = "/tarefas";
    private static final String URL_ID = "/tarefas/{id}";

    @Nested
    @DisplayName("3. Testes para o cadastro de tarefas")
    public class TarefaCadastroTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("3.1. Deve retornar status 201 ao cadastrar uma nova tarefa")
        public void deveRetornarStatus201AoCadastrarUmaNovaTarefa() throws Exception {

            var body = """
                    {
                        "nome": "Desenvolvimento do módulo de vendas",
                        "descricao": "Desenvolvimento do módulo de vendas do sistema ERP.",
                        "prioridade": "Alta",
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(11))
                    .andExpect(jsonPath("$.nome").value("Desenvolvimento do módulo de vendas"))
                    .andExpect(jsonPath("$.descricao").value("Desenvolvimento do módulo de vendas do sistema ERP."))
                    .andExpect(jsonPath("$.concluida").value(false))
                    .andExpect(jsonPath("$.prioridade").value("Alta"))
                    .andExpect(jsonPath("$.projeto.id").value(2))
                    .andExpect(jsonPath("$.projeto.nome").value("Reformulação do ERP"))
                    .andExpect(jsonPath("$.projeto.descricao").value("Projeto de atualização e integração do sistema ERP."))
                    .andExpect(jsonPath("$.projeto.dataInicio").value("2023-02-20"))
                    .andExpect(jsonPath("$.projeto.dataFim").value("2023-12-10"));
        }

        @Test
        @DisplayName("3.2. Deve retornar status 400 ao cadastrar uma nova tarefa com nome nulo")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComNomeNulo() throws Exception {

            var body = """
                    {
                        "nome": null,
                        "descricao": "Desenvolvimento do módulo de vendas do sistema ERP.",
                        "prioridade": "Alta",
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.3. Deve retornar status 400 ao cadastrar uma nova tarefa com nome vazio")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComNomeVazio() throws Exception {

            var body = """
                    {
                        "nome": "",
                        "descricao": "Desenvolvimento do módulo de vendas do sistema ERP.",
                        "prioridade": "Alta",
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.4. Deve retornar status 400 ao cadastrar uma nova tarefa com nome em branco")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComNomeEmBranco() throws Exception {

            var body = """
                    {
                        "nome": " ",
                        "descricao": "Desenvolvimento do módulo de vendas do sistema ERP.",
                        "prioridade": "Alta",
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.5. Deve retornar status 400 ao cadastrar uma nova tarefa com descrição nula")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComDescricaoNula() throws Exception {

            var body = """
                    {
                        "nome": "Desenvolvimento do módulo de vendas",
                        "descricao": null,
                        "prioridade": "Alta",
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.6. Deve retornar status 400 ao cadastrar uma nova tarefa com descrição vazia")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComDescricaoVazia() throws Exception {

            var body = """
                    {
                        "nome": "Desenvolvimento do módulo de vendas",
                        "descricao": "",
                        "prioridade": "Alta",
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.7. Deve retornar status 400 ao cadastrar uma nova tarefa com descrição em branco")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComDescricaoEmBranco() throws Exception {

            var body = """
                    {
                        "nome": "Desenvolvimento do módulo de vendas",
                        "descricao": " ",
                        "prioridade": "Alta",
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.8. Deve retornar status 400 ao cadastrar uma nova tarefa com prioridade nula")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComPrioridadeNula() throws Exception {

            var body = """
                    {
                        "nome": "Desenvolvimento do módulo de vendas",
                        "descricao": "Desenvolvimento do módulo de vendas do sistema ERP.",
                        "prioridade": null,
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.9. Deve retornar status 400 ao cadastrar uma nova tarefa com prioridade vazia")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComPrioridadeVazia() throws Exception {

            var body = """
                    {
                        "nome": "Desenvolvimento do módulo de vendas",
                        "descricao": "Desenvolvimento do módulo de vendas do sistema ERP.",
                        "prioridade": "",
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.10. Deve retornar status 400 ao cadastrar uma nova tarefa com prioridade em branco")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComPrioridadeEmBranco() throws Exception {

            var body = """
                    {
                        "nome": "Desenvolvimento do módulo de vendas",
                        "descricao": "Desenvolvimento do módulo de vendas do sistema ERP.",
                        "prioridade": " ",
                        "projetoId": 2
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.11. Deve retornar status 400 ao cadastrar uma nova tarefa com projetoId nulo")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComProjetoIdNulo() throws Exception {

            var body = """
                    {
                        "nome": "Desenvolvimento do módulo de vendas",
                        "descricao": "Desenvolvimento do módulo de vendas do sistema ERP.",
                        "prioridade": "Alta",
                        "projetoId": null
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("3.12. Deve retornar status 400 ao cadastrar uma nova tarefa com projetoId inexistente")
        public void deveRetornarStatus400AoCadastrarUmaNovaTarefaComProjetoIdInexistente() throws Exception {

            var body = """
                    {
                        "nome": "Desenvolvimento do módulo de vendas",
                        "descricao": "Desenvolvimento do módulo de vendas do sistema ERP.",
                        "prioridade": "Alta",
                        "projetoId": 42
                    }
                    """;

            mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("2. Testes para a busca de todas as tarefas")
    public class TarefaListagemTest {
        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("2.1. Deve retornar status 200 ao buscar todas as tarefas")
        public void deveRetornarStatus200AoBuscarTodasAsTarefas() throws Exception {
            mockMvc.perform(get(URL))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(10))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].nome").value("Design do site"))
                    .andExpect(jsonPath("$[0].descricao").value("Design da interface do usuário para o novo site."))
                    .andExpect(jsonPath("$[0].concluida").value(false))
                    .andExpect(jsonPath("$[0].prioridade").value("Alta"))
                    .andExpect(jsonPath("$[0].projeto.id").value(1))
                    .andExpect(jsonPath("$[0].projeto.nome").value("Desenvolvimento Web"))
                    .andExpect(jsonPath("$[0].projeto.descricao").value("Criação de um novo site corporativo."))
                    .andExpect(jsonPath("$[0].projeto.dataInicio").value("2023-01-10"))
                    .andExpect(jsonPath("$[0].projeto.dataFim").value("2023-06-15"));
        }

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("2.2. Deve retornar status 204 ao buscar todas as tarefas e não houver tarefas cadastradas")
        public void deveRetornarStatus204AoBuscarTodasAsTarefasENaoHouverTarefasCadastradas() throws Exception {
            mockMvc.perform(get(URL))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DisplayName("1. Testes para a busca de tarefas por id")
    public class TarefaBuscaTest {
        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("1.1. Deve retornar status 200 ao buscar tarefa por id")
        public void deveRetornarStatus200AoBuscarTarefaPorId() throws Exception {
            mockMvc.perform(get(URL_ID, 4))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(4))
                    .andExpect(jsonPath("$.nome").value("Integração de módulos ERP"))
                    .andExpect(jsonPath("$.descricao").value("Integração dos módulos de contabilidade e vendas."))
                    .andExpect(jsonPath("$.concluida").value(true))
                    .andExpect(jsonPath("$.prioridade").value("Alta"))
                    .andExpect(jsonPath("$.projeto.id").value(2))
                    .andExpect(jsonPath("$.projeto.nome").value("Reformulação do ERP"))
                    .andExpect(jsonPath("$.projeto.descricao").value("Projeto de atualização e integração do sistema ERP."))
                    .andExpect(jsonPath("$.projeto.dataInicio").value("2023-02-20"))
                    .andExpect(jsonPath("$.projeto.dataFim").value("2023-12-10"));
        }

        @Test
        @DisplayName("1.2. Deve retornar status 404 ao buscar tarefa por id inexistente")
        public void deveRetornarStatus404AoBuscarTarefaPorIdInexistente() throws Exception {
            mockMvc.perform(get(URL_ID, 42))
                    .andExpect(status().isNotFound());
        }

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("1.3. Deve retornar status 404 ao buscar tarefa e não houver tarefas cadastradas")
        public void deveRetornarStatus404AoBuscarTarefaENaoHouverTarefasCadastradas() throws Exception {
            mockMvc.perform(get(URL_ID, 1))
                    .andExpect(status().isNotFound());
        }
    }
}