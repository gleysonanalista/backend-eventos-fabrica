# Eventos Fábrica - Backend

API REST Spring Boot para receber eventos de equipamentos industriais.

## Requisitos

- Ide utilizada IntelliJ IDEA
- Java 21

Baixar para Windows, Mac ou Linux (pelo site), Acesse https://adoptium.net/temurin/releases/
Escolha Version: 21 - LTS, o seu sistema operacional e JDK.
Baixe e instale com as opções padrão. No instalador do Windows, marque as opções “Set JAVA_HOME variable” e “Add to PATH”.

Conferir a instalação - abra o cmd e digite os comandos abaixo
java -version
javac -version

Os dois devem mostrar a versão 21.x. O javac só existe no JDK, então ele confirma que você instalou o JDK e não só o JRE.

Depois de instalar
Se o comando ainda mostrar outra versão, é porque há um Java mais antigo no PATH. Confira a variável JAVA_HOME e ponha o JDK 21 na frente.
Na sua IDE (IntelliJ ou Eclipse), selecione o JDK 21 em Project Structure → SDK.

- Maven 3.6+ (ou Maven Wrapper, se adicionado pelo IDE) - Obs o Mavem já vem adicionado na IDE Intellij

## Executar

### No IntelliJ IDEA

1. Abra o projeto no IntelliJ IDEA através de **File > Open**.
2. Abra a classe `EventosFabricaApplication`.
3. Clique na seta verde ao lado da classe e selecione **Run 'EventosFabricaApplication.main()'**.
4. Aguarde a inicialização do Spring Boot.
5. Obs: a aplicação Backend utiliza o Banco de dados H2: ele é um banco em memória.

## API e Banco de Dados e APIS

### URLs Locais

- API: `http://localhost:8080/api/events`
- Console H2: `http://localhost:8080/h2-console`

### Configuração H2

- JDBC URL: `jdbc:h2:mem:eventosdb`
- User: `sa`
- Password: em branco

### Endpoints

| Método | Endpoint | Finalidade                       |
|--------|----------|----------------------------------|
| POST   | `/api/events` | Cadastrar um evento              |
| GET    | `/api/events` | Consultar os eventos registrados |
| DELETE | `/api/events/{eventId}` | deletar o evento na lista |

## Arquitetura

O backend é organizado em camadas para separar responsabilidades.

## Fluxo de Processamento

1. A API recebe a requisição.
2. Os dados são validados.
3. O sistema verifica se o `eventId` já existe.
4. Para eventos do tipo `PRODUCTION`, apenas o evento é registrado.
5. Para eventos do tipo `QUALITY`, evento e auditoria são gravados na mesma transação.
6. A API retorna a resposta apropriada.

## Respostas HTTP

- `201 Created`
- `400 Bad Request`
- `409 Conflict`

## Possíveis evoluções

A arquitetura permite evoluções futuras, como autenticação e autorização, testes automatizados adicionais, monitoramento, mensageria e processamento assíncrono. Esses recursos devem ser considerados extensões futuras, caso sejam necessários; não são presumidos como já implementados.

