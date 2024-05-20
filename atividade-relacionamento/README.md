# Gerenciador de tarefas

Imagine que você é um desenvolvedor de software freelancer e foi contratado por uma empresa de consultoria para criar um aplicativo de gerenciamento de projetos e tarefas. A empresa trabalha com diversos clientes simultaneamente, cada um com seus próprios projetos e tarefas associadas. Eles precisam de uma plataforma centralizada para acompanhar o progresso de cada projeto e garantir que todas as tarefas sejam concluídas dentro do prazo.

<hr style="background-color:gray; height:6px">
<h1 style="color:red">🚨Atenção!🚨</h1>
<h3>Não altere:</h3>
<ul>
  <li><h3>application.properties</h3></li>
  <li><h3>data.sql, que já inclui algumas tarefas e projetos cadastrados.</h3></li>
  <li><h3>Não esqueça da camada de serviço para realizar as regras e consultas banco.</h3></li>
</ul>


<br>
<hr style="background-color:gray; height:6px">

# Entidades
- **Projeto**
    - id: Integer
    - nome: String
    - descricao: String
    - dataInicio: LocalDate
    - dataFim: LocalDate
    - tarefas: List de Tarefas
<hr/>

- **Tarefa**
    - id: Integer
    - nome: String
    - descricao: String
    - concluida: boolean
    - prioridade: String
    - projeto: Projeto

# DTO's
- **ProjetoCriacaoDto**
    - nome: String
        - Não deve ter espaços em branco, nulo ou vazio
    - descricao: String
        - Não deve ter espaços em branco, nulo ou vazio
    - dataInicio: LocalDate
        - Não deve ser nulo, deve ter uma data futura ou presente
    - dataFim: LocalDate
        - Não deve ser nulo, deve ter uma data futura
<hr/>

- **ProjetoListagemDto**
    - id: Integer
    - nome: String
    - descricao: String
    - dataInicio: LocalDate
    - dataFim: LocalDate
    - tarefas: List de TarefaListagemDto
    - **TarefaListagemDto (Nested class)**
        - id: Integer
        - nome: String
        - descricao: String
        - concluida: boolean
        - prioridade: String
<hr/>

- **TarefaCriacaoDto**
    - nome: String
        - Não deve ter espaços em branco, nulo ou vazio
    - descricao: String
        - Não deve ter espaços em branco, nulo ou vazio
    - prioridade: String
        - Não deve ter espaços em branco, nulo ou vazio
    - projetoId: Integer
        - Não deve ser nulo
<hr/>

- **TarefaListagemDto**
    - id: Integer
    - nome: String
    - descricao: String
    - concluida: boolean
    - prioridade: String
    - projeto: ProjetoListagemDto
    - **ProjetoListagemDto (Nested class)**
        - id: Integer
        - nome: String
        - descricao: String
        - dataInicio: LocalDate
        - dataFim: LocalDate

# Endpoints

## 📄 Listagem de Projetos

- **Endpoint**: `GET /projetos`
- **Descrição**: Retorna todos os projetos cadastrados.

## 📝 Busca projeto por ID

- **Endpoint**: `GET /projetos/{id}`
- **Descrição**: Retorna o projeto por id.

## 📋 Criação de um projeto

- **Endpoint**: `POST /projetos`
- **Descrição**: Permite a criação de um novo projeto. Caso a dataInicio seja uma data maior que a dataFim retorne 400.

<br>
<hr style="background-color:gray; height:6px">

## 📄 Listagem de tarefas

- **Endpoint**: `GET /tarefas`
- **Descrição**: Retorna todos as tarefas cadastrados.

## 📝 Busca tarefa por ID

- **Endpoint**: `GET /tarefas/{id}`
- **Descrição**: Retorna a tarefa por id.

## 📋 Criação de uma tarefa

- **Endpoint**: `POST /tarefas`
- **Descrição**: Permite a criação de um nova tarefa. Busca o projeto informado e vincule a tarefa.