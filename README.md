# DevForum-API

## Descrição 
API REST de fórum desenvolvida com Spring Boot, utilizando autenticação JWT, controle de acesso por roles e relacionamento entre usuários, cursos, tópicos e respostas.

## Tecnologias
* Java 21
* Spring Boot
* Spring Security
* JWT
* PostgreSQL
* Hibernate
* JPA
* Maven
* Lombok
* Validation

## Funcionalidades
* Cadastro de usuários
* Login com JWT
* CRUD de tópicos
* Respostas com marcação de solução
* Proteção de rotas
* Controle de acesso por dono
* Soft delete
* Validações

## Fluxo

1. Cadastrar usuário
2. Criar curso
3. Login
4. Criar tópicos
5. Responder tópicos
6. Atualizar o tópico
7. Deletar apenas como autor

## EndPoints

| Método |      Rota      |          Descrição           |
|        |                |                              |
| POST   |   /usuarios    |          Cadastro            |
| POST   |   /cursos      |         Criar curso          |
| POST   |   /auth/login  |            Login             |
| POST   |   /topicos     |         Criar tópico         |
|        |                |                              |
| GET    |  /topicos      |   Listar todos os tópicos    |
| GET    |  /topicos/{id} |   Mais detalher do tópico    |
|        |                |                              |
| PUT    |   /topicos     | Atualizar o título do tópico |
|        |                |                              |
| DELETE | /topicos/{id}  |           Deletar            |
|        |                |                              |
| POST   |   /repostas    |        Criar resposta        |

## Autenticação
Authorization: Bearer TOKEN

## Configuração
spring.datasource.url
spring.datasource.username
spring.datasource.password

api.security.token.secret
api.security.token.expiration
