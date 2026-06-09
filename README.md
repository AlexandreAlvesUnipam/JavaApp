# JavaApp

#### Exemplo Java com Spring para a disciplina de Programação Orientada a Objetos.

**Instale as extensões**:
- Extension Pack for Java;
- Spring Initializr Java Support

**Crie um projeto Maven**:

- Ctrl+p ou F2;
- Selecione "Projeto Maven"

**Banco de dados**:
- O banco está em um serviço dentro de um container. Esse container é gerenciado pelo `docker-compose.yml`. Para iniciar o serviço do banco basta executar `docker-compose up -d`. Neste arquivo, insira qual será a sua senha do banco.

**Pom.xml**:
- Verifique as dependências.

**Execução**:
- Execute o projeto java e verifique se os dados foram inseridos no banco.