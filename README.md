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
- Execute o projeto Java, através da classe principal;
- Faça uma requisição POST para simular uma compra: `curl -X POST http://localhost:8080/api/pedidos/simular-compra`
- Entre no container do banco para verificar se a compra foi registrada: `docker exec -it vendas_db psql -U admin -d vendasdb`
- Execute a query: `SELECT * FROM tb_pedidos;`