INSERT INTO Projeto (nome, descricao, data_inicio, data_fim) VALUES ('Desenvolvimento Web', 'Criação de um novo site corporativo.', '2023-01-10', '2023-06-15');
INSERT INTO Projeto (nome, descricao, data_inicio, data_fim) VALUES ('Reformulação do ERP', 'Projeto de atualização e integração do sistema ERP.', '2023-02-20', '2023-12-10');
INSERT INTO Projeto (nome, descricao, data_inicio, data_fim) VALUES ('Campanha de Marketing', 'Desenvolvimento de uma campanha de marketing digital.', '2023-03-01', '2023-05-20');
INSERT INTO Projeto (nome, descricao, data_inicio, data_fim) VALUES ('Implementação de CRM', 'Integração de um novo sistema CRM com outros sistemas internos.', '2023-04-05', '2023-09-30');
INSERT INTO Projeto (nome, descricao, data_inicio, data_fim) VALUES ('Expansão Internacional', 'Projeto de expansão das operações para outros países.', '2023-05-15', '2024-01-15');

INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Design do site', 'Design da interface do usuário para o novo site.', false, 'Alta', 1);
INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Codificação do Front-end', 'Implementação do front-end em React.', true, 'Alta', 1);
INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Preparar infraestrutura ERP', 'Configuração de servidores para o novo ERP.', false, 'Média', 2);
INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Integração de módulos ERP', 'Integração dos módulos de contabilidade e vendas.', true, 'Alta', 2);
INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Elaboração de conteúdo', 'Criação de conteúdo para redes sociais.', true, 'Baixa', 3);
INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Análise de métricas', 'Análise de métricas de campanha e ajustes.', false, 'Média', 3);
INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Configuração CRM', 'Configuração do sistema CRM.', false, 'Alta', 4);
INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Testes de integração CRM', 'Realizar testes de integração do CRM com outros sistemas.', true, 'Alta', 4);
INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Análise de mercado', 'Análise de mercado para identificar países-alvo.', false, 'Média', 5);
INSERT INTO Tarefa (nome, descricao, concluida, prioridade, projeto_id) VALUES ('Plano de expansão', 'Desenvolvimento do plano de negócios para expansão.', true, 'Alta', 5);
