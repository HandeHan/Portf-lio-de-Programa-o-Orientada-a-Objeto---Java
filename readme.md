# Sistema de Registro de Denúncias – Administração

Este projeto apresenta uma aplicação simples desenvolvida em Java (Android) para cadastro e autenticação de usuários, compondo a parte administrativa mínima de um sistema de registro de denúncias públicas, conforme requisitos do portfólio de Programação Orientada a Objetos.

A aplicação utiliza banco de dados local SQLite para armazenar informações dos usuários, permitindo operações essenciais como cadastro e login.

---

## Funcionalidades Implementadas (Lado Administrador)

- Cadastro de usuários.
- Login com validação de e-mail e senha.
- Estrutura mínima do banco de dados integrada ao SQLite.
- Organização das camadas de classes (Model, DAO e Database Helper).

---

## Banco de Dados (SQLite)

A estrutura mínima utilizada no projeto possui somente uma tabela (users), suficiente para autenticação de usuários.

### Script SQL utilizado:

```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);

```

+-----------------------+           +---------------------------+
|       Usuario         |           |         Denuncia          |
+-----------------------+           +---------------------------+
| - id : int            |           | - id : int                |
| - nome : String       |           | - usuarioId : int         |
| - email : String      |           | - descricao : String      |
| - senha : String      |           | - status : String         |
| - administrador:bool  |           +---------------------------+
+-----------------------+           | +resolver()               |
| +getId()              |           +---------------------------+
| +getNome()            |
| +getEmail()           |     (Denuncia.usuarioId -> Usuario.id)
| +isAdministrador()    |
+-----------------------+

       ^                          ^
       |                          |
       | contains                 | contains
+-------------------+      +-----------------------+
| UsuarioRepository |      | DenunciaRepository   |
+-------------------+      +-----------------------+
| - List<Usuario>   |      | - List<Denuncia>      |
| - sequence:int    |      | - sequence:int        |
+-------------------+      +-----------------------+
| +cadastrar(...)   |      | +registrar(...)       |
| +buscarPorEmail...|      | +listar()             |
+-------------------+      | +buscarPorId(...)     |
                           +-----------------------+

       ^                          ^
       | uses                     | uses
+-------------------+      +-----------------------+
| UsuarioService    |      | DenunciaService       |
+-------------------+      +-----------------------+
| - repo            |      | - repo                |
+-------------------+      +-----------------------+
| +cadastrar(...)   |      | +registrar(...)       |
| +login(...)       |      | +listar()             |
+-------------------+      | +resolver(id)         |
                           +-----------------------+

                             ^
                             |
                       +-------------+
                       |    Main     |
                       +-------------+
                       | +main(...)  |
                       +-------------+
                       (interage com services via console)


Tecnologias Utilizadas

- Java
- Android SDK
- SQLite
- Padrão DAO (Data Access Object)
- MVC simplificado
