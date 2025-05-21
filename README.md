# ON-Data: Sistema de Gestão de Ocorrências Odontológicas

![374370531-87ad94eb-a00e-43b5-84c4-fbf7f7b9fd7d](https://github.com/user-attachments/assets/1eed88d3-136a-4f14-903d-60963a949a3d)

Link do Projeto: [https://github.com/eduardofuncao/on-data-thymeleaf](https://github.com/eduardofuncao/on-data-thymeleaf)

## Sobre o projeto

ON-Data é um sistema de gestão para clínicas odontológicas desenvolvido com Spring Boot e Thymeleaf. O sistema permite o gerenciamento de pacientes, registro de ocorrências odontológicas, aprovação de procedimentos e visualização de dashboard com estatísticas.

## Integrantes do Grupo

- Eduardo Função - RM553362
- Artur Fiorindo - RM553481
- Jhoe Hashimoto - RM553831

## Funcionalidades

- **Gestão de Pacientes:** Cadastro, edição e exclusão de pacientes com informações pessoais.
- **Registro de Ocorrências:** Associação de pacientes, dentistas e doenças em registros detalhados.
- **Fluxo de Aprovação:** Aprovação de ocorrências registradas.
- **Dashboard de Relatórios:** Visualização de estatísticas e métricas a respeito das ocorrências.
- **Autenticação e Gestão de Usuários:** Gerenciamento de usuários e admins.
- **Envio de mensagens usando RabbitMQ para o serviço de integração com o Telegram:** Serviço consumidor disponível em 
https://github.com/eduardofuncao/on-data-thymeleaf-consumer. A aplicação consumidora envia os dados para um bot do telegram, 
personalizando cada mensagem utilizando spring AI conectado com um modelo de IA através do Ollama.
- **Actuator:** Monitoramento da aplicação.
- **Internacionalização:** Suporte para Inglês e Português.
- **Containerização:** Projeto Completo pode ser executado através do arquivo `docker-compose.yml`

## Tecnologias Utilizadas
- **Backend:**
  - Java 22
  - Spring Boot 3
  - Spring Data JPA
  - Actuator
  - Spring Security
  - RabbitMQ
  - Spring AI
  - Telegram Bots

- **Frontend:**
  - Thymeleaf
  - Bootstrap 5.3
  - Chart.js

- **Banco de Dados:**
  - H2 Database (desenvolvimento)
  - Oracle (produção)

## Diagrama Entidade Relacionamento
![384556710-1f80d9cb-08d4-4d96-8207-a67ef1b229ce](https://github.com/user-attachments/assets/7af3ff08-2465-4a2f-8c95-26cc754a14b1)

## Arquitetura Simplificada do Projeto
![on-data-thymeleaf drawio(1)](https://github.com/user-attachments/assets/ab43de18-b4e4-4185-b5d0-9cffd86e089d)


## Instalação e Execução
### Pré-requisitos
- JDK 17 ou superior
- Gradle

### Passos para execução Local
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/on-data-thymeleaf.git
   cd on-data-thymeleaf
   ```

2. Compile e execute o projeto:

3. Acesse a aplicação:
   ```
   http://localhost:8080
   ```
### Execução através do Docker
O projeto completo (consumidor, produtor, rabbitMQ, Ollama) pode ser executado através do arquivo `docker-compose.yml`
utilizando o seguinte comando:
```bash
docker-compose up -d
```
> Note que nessa execução, o arquivo entrypoint.sh é utilizando para obter o modelo llama3.2:3b automaticamente quando
> o container é iniciado, fazendo com que a primeira execução possa demorar mais do que o esperado.

### Fotos da Aplicação
![image](https://github.com/user-attachments/assets/d2f8a2a4-6e30-427f-a8b4-2e7ccde3765c)
![image](https://github.com/user-attachments/assets/21c85d87-c47f-4b5f-9915-6d035f5c6d16)
![image](https://github.com/user-attachments/assets/081f1b76-6b8d-42cd-9278-43ccaa852c88)
![image](https://github.com/user-attachments/assets/70a4ce48-027d-4d21-843f-b52b3868c46e)
![image](https://github.com/user-attachments/assets/936e3573-0776-4cef-8f67-868b51ef983d)


## Video Demo

## Roadmap de Funcionalidades Futuras

- [x] Autenticação e autorização com Spring Security
- [x] Internacionalização
- [x] Recursos de Mensageria
- [x] Monitoramento com Spring Boot Actuator
- [x] Implementação de recursos de IA com Spring AI
- [x] Conteinerização
