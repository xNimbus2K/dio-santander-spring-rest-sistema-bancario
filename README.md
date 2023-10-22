# Sistema Bancário Spring Boot

Este é um projeto de exemplo de um sistema bancário desenvolvido em Spring Boot, um aplicativo Java que permite realizar operações bancárias, como depósitos, saques e transferências entre contas. Este projeto é um exemplo simples para demonstrar o uso de Spring Boot em uma aplicação RESTful.

## Requisitos

- Java Development Kit (JDK) 8 ou superior
- Maven (opcional, mas recomendado para gerenciamento de dependências)
- IDE Java (por exemplo, IntelliJ IDEA ou Eclipse)

## Configuração

1. Clone este repositório em sua máquina local.
2. Importe o projeto em sua IDE Java de escolha.
3. Certifique-se de que todas as dependências do Maven sejam resolvidas automaticamente.
4. Configure seu banco de dados e as propriedades de conexão no arquivo `application.properties`.
5. Execute o projeto. A aplicação será executada em http://localhost:8080 por padrão.

## Endpoints

O projeto oferece os seguintes endpoints:

- **POST /transactions/deposit:** Realiza um depósito em uma conta.
- **POST /transactions/withdraw:** Realiza um saque em uma conta.
- **POST /transactions/transfer:** Realiza uma transferência entre contas.
- **GET /transactions/balance/{accountNumber}:** Consulta o saldo de uma conta.
- **GET /transactions/history/{accountNumber}:** Consulta o histórico de transações de uma conta (será implementado totalmente).

## Exemplo de Uso no Postman

Você pode usar o Postman para testar os endpoints. Aqui está um exemplo de como usar o endpoint de depósito:

1. Abra o Postman.
2. Configure a solicitação como "POST".
3. Insira a URL completa, por exemplo, "http://localhost:8080/transactions/deposit".
4. No corpo da solicitação, selecione "raw" e defina o tipo de conteúdo como "JSON (application/json)".
5. No corpo da solicitação, insira os detalhes do depósito em formato JSON:

```json
{
    "targetAccount": "123456",
    "amount": 100.0,
    "description": "Depósito inicial"
}
```
Clique no botão "Send" para enviar a solicitação.
Isso enviará uma solicitação de depósito para o sistema bancário.
