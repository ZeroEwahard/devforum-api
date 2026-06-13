# 💬👥DevForum-API

## 📓Descrição 
API REST de fórum desenvolvida com Spring Boot, utilizando autenticação JWT, controle de acesso por roles e relacionamento entre usuários, cursos, tópicos e respostas. <br>
#ALURA #ORACLE

## 🛠️Tecnologias
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

## 💡Funcionalidades
* Cadastro de usuários
* Login com JWT
* CRUD de tópicos
* Respostas com marcação de solução
* Proteção de rotas
* Controle de acesso por dono
* Soft delete
* Validações

## ➡️Fluxo

1. Cadastrar usuário
2. Criar curso
3. Login
4. Criar tópicos
5. Responder tópicos
6. Atualizar o tópico
7. Deletar apenas como autor

## 🎯EndPoints
```bash
| Método |      Rota      |          Descrição           |<br>
<br>
| POST   |   /usuarios    |          Cadastro            |<br>
| POST   |   /cursos      |         Criar curso          |<br>
| POST   |   /auth/login  |            Login             |<br>
| POST   |   /topicos     |         Criar tópico         |<br>
<br>
| GET    |  /topicos      |   Listar todos os tópicos    |<br>
| GET    |  /topicos/{id} |   Mais detalher do tópico    |<br>
<br>
| PUT    |   /topicos     | Atualizar o título do tópico |<br>
<br>
| DELETE | /topicos/{id}  |           Deletar            |<br>
<br>
| POST   |   /repostas    |        Criar resposta        |<br>
```

## 🔐Autenticação
Authorization: Bearer TOKEN

## ⚙️Configuração
spring.datasource.url <br>
spring.datasource.username <br>
spring.datasource.password <br>

api.security.token.secret <br>
api.security.token.expiration
