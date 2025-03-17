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
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Lucas Mendes', 'MASCULINO', 28, 'Rua das Acácias, 321', 'São Paulo', false, 3000.00, 2.5, 'A');
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Fernanda Lima', 'FEMININO', 34, 'Avenida das Palmeiras, 654', 'Rio de Janeiro', true, 4500.00, 3.0, 'B');
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Ricardo Alves', 'MASCULINO', 45, 'Rua dos Ipês, 987', 'Belo Horizonte', false, 6000.00, 1.5, 'A');
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Patrícia Santos', 'FEMININO', 29, 'Avenida das Flores, 1112', 'São Paulo', true, 3500.00, 2.0, 'C');
INSERT INTO Paciente (nome, genero, idade, endereco, cidade, fumante, renda, visitas_por_ano, categoria) VALUES ('Gabriel Costa', 'NAO_BINARIO', 50, 'Rua das Orquídeas, 1314', 'Curitiba', false, 7000.00, 4.0, 'B');