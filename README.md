# Desafio Validação e Segurança

API REST de eventos e cidades desenvolvida em Java com Spring Boot, implementando controle de acesso por perfil de usuário (CLIENT/ADMIN) e validações customizadas de negócio.

Projeto desenvolvido como desafio prático do curso **Java Spring Expert**, da [DevSuperior](https://devsuperior.com.br).

## Sobre o desafio

O projeto consiste em um sistema de eventos e cidades, com uma relação N-1 entre eles (um evento pertence a uma cidade, uma cidade pode ter vários eventos). O objetivo era implementar, a partir de um conjunto de testes de integração já fornecidos, as regras de autorização e validação necessárias para que todos os testes passassem.

## Modelo de domínio

- **City**: `id`, `name`
- **Event**: `id`, `name`, `date`, `url`, `city` (relacionamento `@ManyToOne` com City)
- **User**: `id`, `email`, `password`, `roles` (relacionamento `@ManyToMany` com Role)
- **Role**: `id`, `authority`

## Regras de negócio

### Controle de acesso

| Ação | Quem pode acessar |
|---|---|
| Leitura (`GET`) de eventos e cidades | Público (não requer login) |
| Inserir evento (`POST /events`) | CLIENT ou ADMIN |
| Demais ações (ex: `POST /cities`) | Apenas ADMIN |

### Validação de City

- Nome não pode ser vazio

### Validação de Event

- Nome não pode ser vazio
- Data não pode ser passada
- Cidade não pode ser nula

## Tecnologias

- Java
- Spring Boot
- Spring Security (OAuth2 Authorization Server + Resource Server, grant type `password` customizado)
- Spring Data JPA / Hibernate
- Bean Validation (Jakarta Validation)
- H2 Database (ambiente de testes)
- JUnit / MockMvc (testes de integração)
- Maven

## Segurança

A autenticação é feita via OAuth2 com JWT, usando um grant type customizado do tipo `password`. O controle de autorização por rota é feito através da anotação `@PreAuthorize`, diretamente nos métodos dos controllers.

## Tratamento de exceções

Um `@ControllerAdvice` centraliza o tratamento de:

- Erros de validação (`422 Unprocessable Entity`), retornando o campo e a mensagem de cada erro
- Recursos não encontrados (`404 Not Found`), como uma cidade inexistente referenciada em um evento

## Como rodar os testes

O projeto já possui a suíte de testes de integração (perfil `test`, banco H2 em memória). Para rodar:

```bash
mvn test
```

São 12 testes cobrindo autenticação, autorização por perfil e validação de dados.

## Créditos

Desafio proposto no curso **Java Spring Expert**, da [DevSuperior](https://devsuperior.com.br).

## Desenvolvido por:

Breno Oliveira de Souza
