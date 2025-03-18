DELETE FROM Ocorrencia;
DELETE FROM Paciente;
DELETE FROM Doenca;
DELETE FROM Dentista;
DELETE FROM Clinica;

-- Insert Clinicas
INSERT INTO Clinica (nome, endereco, cidade, custo_medio_consulta) VALUES ('Clinica Sorriso Feliz', 'Rua das Flores, 123', 'São Paulo', 150.00);
INSERT INTO Clinica (nome, endereco, cidade, custo_medio_consulta) VALUES ('Clinica Dental Care', 'Avenida Brasil, 456', 'Rio de Janeiro', 200.00);
INSERT INTO Clinica (nome, endereco, cidade, custo_medio_consulta) VALUES ('Clinica DentArt', 'Rua das Palmeiras, 789', 'Belo Horizonte', 180.00);
INSERT INTO Clinica (nome, endereco, cidade, custo_medio_consulta) VALUES ('Clinica OdontoPlus', 'Avenida Paulista, 1011', 'São Paulo', 250.00);
INSERT INTO Clinica (nome, endereco, cidade, custo_medio_consulta) VALUES ('Clinica Sorriso Perfeito', 'Rua dos Pinheiros, 1213', 'Curitiba', 220.00);

-- Insert Dentistas
INSERT INTO Dentista (nome, idade, registro, salario, clinica_id) VALUES ('Dr. João Silva', 35, '12345', 5000.00, 1);
INSERT INTO Dentista (nome, idade, registro, salario, clinica_id) VALUES ('Dra. Maria Oliveira', 40, '67890', 5500.00, 2);
INSERT INTO Dentista (nome, idade, registro, salario, clinica_id) VALUES ('Dr. Pedro Souza', 38, '54321', 5200.00, 3);
INSERT INTO Dentista (nome, idade, registro, salario, clinica_id) VALUES ('Dra. Ana Costa', 42, '98765', 6000.00, 4);
INSERT INTO Dentista (nome, idade, registro, salario, clinica_id) VALUES ('Dr. Carlos Pereira', 37, '11223', 5300.00, 5);

-- Insert Doencas
INSERT INTO Doenca (nome, taxa_reincidencia, gravidade) VALUES ('Cárie', 0.3, 'MEDIA');
INSERT INTO Doenca (nome, taxa_reincidencia, gravidade) VALUES ('Gengivite', 0.2, 'BAIXA');
INSERT INTO Doenca (nome, taxa_reincidencia, gravidade) VALUES ('Periodontite', 0.4, 'ALTA');
INSERT INTO Doenca (nome, taxa_reincidencia, gravidade) VALUES ('Bruxismo', 0.1, 'MEDIA');
INSERT INTO Doenca (nome, taxa_reincidencia, gravidade) VALUES ('Sensibilidade Dentária', 0.25, 'BAIXA');

-- Insert Pacientes
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Lucas Mendes', 'MASCULINO', 28, 'Rua das Acácias, 321', 'São Paulo', 0, 3000.00, 2.5, 'A');
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Fernanda Lima', 'FEMININO', 34, 'Avenida das Palmeiras, 654', 'Rio de Janeiro', 1, 4500.00, 3.0, 'B');
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Ricardo Alves', 'MASCULINO', 45, 'Rua dos Ipês, 987', 'Belo Horizonte', 0, 6000.00, 1.5, 'A');
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Patrícia Santos', 'FEMININO', 29, 'Avenida das Flores, 1112', 'São Paulo', 1, 3500.00, 2.0, 'C');
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Gabriel Costa', 'NAO_BINARIO', 50, 'Rua das Orquídeas, 1314', 'Curitiba', 0, 7000.00, 4.0, 'B');

-- Insert Ocorrencias
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-01-15 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC001', 150.00, 2, 1, 1, 1, 1);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-02-20 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC002', 200.00, 1, 0, 2, 2, 2);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-03-25 09:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC003', 180.00, 3, 1, 3, 3, 3);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-04-10 14:15:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC004', 250.00, 2, 1, 4, 4, 4);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-05-05 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC005', 220.00, 1, 0, 5, 5, 5);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-06-12 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC006', 300.00, 4, 1, 1, 2, 3);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-07-18 16:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC007', 350.00, 2, 1, 2, 3, 4);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-08-22 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC008', 400.00, 3, 0, 3, 4, 5);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-09-30 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC009', 450.00, 1, 1, 4, 5, 1);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-10-05 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC010', 500.00, 2, 1, 5, 1, 2);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-11-05 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC011', 150.00, 4, 0, 2, 1, 4);
INSERT INTO Ocorrencia (data_ocorrencia, codigo_ocorrencia, valor, duracao_horas, aprovado, paciente_id, doenca_id, dentista_id) VALUES (TO_DATE('2025-12-05 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'OC012', 100.00, 5, 1, 4, 3, 5);
