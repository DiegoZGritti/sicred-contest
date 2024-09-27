# sicred-contest

## Como Rodar o Projeto pela Primeira Vez com Docker Compose

### Pré-requisitos:
- Docker instalado. Se necessário, você pode baixar o Docker neste [link](https://www.docker.com/get-started).
- Docker Compose já está incluído na instalação padrão do Docker.

### Passos:

1. **Subir os serviços com Docker Compose:**
   ```sh
   docker-compose up -d

Esse comando irá configurar e subir o banco de dados PostgreSQL e outros serviços como Redis, se necessário. O flag -d faz com que os contêineres rodem em background.  
Verificar se os serviços estão rodando: Para garantir que todos os serviços estão rodando corretamente, use o comando:  
docker ps
Acessar a aplicação: Após iniciar o Docker, você pode acessar o Swagger UI para testar as APIs no navegador:
URL do Swagger: http://localhost:8080/swagger-ui.html

2. **Verificar se a versão do java esta correta:**
Foi desenvolvido na versão java 21 oracle GraalJVM

3. **Verificar se o postgres esta online:**
Para verificar pode ser usado o comando netstat -an | grep 5435

4. **Verificar se o postgres esta de pe:**
O projeto é um projeto maven com spring pode ser importado no intellij.
Para buildar o projeto mvm clean install.
E pode ser startado a partir da IDE.

### Fluxo do Projeto

#### 1. **API para Adicionar Pauta**
Método: POST
Endpoint: /api/pautas
Descrição: Cadastra uma nova pauta com título e descrição obrigatórios.
Request Body:
{
  "titulo": "Pauta 1",
  "descricao": "Descrição da Pauta 1"
}
Response:
Status: 201 Created
Body:
{
  "id": "uuid-da-pauta",
  "titulo": "Pauta 1",
  "descricao": "Descrição da Pauta 1"
}

#### 2. **API para Abrir Sessão**
Método: POST
Endpoint: /api/sessoes
Descrição: Abre uma sessão de votação para uma pauta existente.
Request Body:
{
  "pautaId": "uuid-da-pauta",
  "minutosDuracao": 10
}
Response:
Status: 201 Created
Body:
{
  "id": "uuid-da-sessao",
  "pauta": {
    "id": "uuid-da-pauta",
    "titulo": "Pauta 1",
    "descricao": "Descrição da Pauta 1"
  },
  "status": "Aberta",
  "abertura": "data-de-abertura",
  "fechamento": "data-de-fechamento"
}

#### 3. **API para Registrar Voto**
Método: POST
Endpoint: /api/votos
Descrição: Registra um voto (sim ou não) para uma sessão aberta.
Request Body:
{
  "sessaoId": "uuid-da-sessao",
  "cpf": "12345678901",
  "voto": true
}
Response:
Status: 201 Created
Body:
{
  "id": "uuid-do-voto",
  "sessaoId": "uuid-da-sessao",
  "cpf": "12345678901",
  "voto": true
}

#### 4. **API para Obter Resultado da Votação**
Método: GET
Endpoint: /api/votos/sessao/{sessaoId}/resultado
Descrição: Retorna o resultado da votação de uma sessão após o fechamento.
Response:
Status: 200 OK
Body:
{
  "totalVotos": 5,
  "sim": 3,
  "nao": 2,
  "resultadoFinal": "Aprovado"
}

#### 5. **API para Listar Sessões**
##### Método: GET
Endpoint: /api/sessoes?status=Aberta
Descrição: Lista todas as sessões filtradas por status (Aberta/Fechada). Caso o parâmetro status não seja informado, todas as sessões serão listadas.
Response:
Status: 200 OK
Body:
[
  {
    "id": "uuid-da-sessao",
    "pauta": {
      "id": "uuid-da-pauta",
      "titulo": "Pauta 1",
      "descricao": "Descrição da Pauta 1"
    },
    "status": "Aberta",
    "abertura": "data-de-abertura",
    "fechamento": "data-de-fechamento"
  }
]
