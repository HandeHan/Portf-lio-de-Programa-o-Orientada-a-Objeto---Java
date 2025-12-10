# Sistema de Registro de Denúncias – Administração

Este projeto apresenta uma aplicação simples desenvolvida em Java (Android) para cadastro e autenticação de usuários, compondo a parte administrativa mínima de um sistema de registro de denúncias públicas, conforme requisitos do portfólio de Programação Orientada a Objetos.

A aplicação utiliza banco de dados local SQLite para armazenar informações dos usuários, permitindo operações essenciais como cadastro e login.

---

## Funcionalidades Implementadas (Lado Administrador)

- Cadastro de usuários.
- Login com validação de e-mail e senha.
- Estrutura mínima do banco de dados integrada ao SQLite.
- Organização das camadas de classes (Model, Repository, Service, App).

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

---

## Diagrama de Classes

*Figura: Diagrama de Classes com ASCCI - visão geral das camadas Model / Repository / Service / App*

```sql

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

```

---

## Explicação das Classes do Projeto (Lado Administrador)

Esta aplicação utiliza Programação Orientada a Objetos para organizar as funcionalidades do lado administrador, permitindo gerenciar usuários e denúncias públicas. A arquitetura foi estruturada em camadas (Model, Repository, Service e App) para facilitar a organização, manutenção e clareza do código.

1. Classe Usuario

A classe Usuario representa uma pessoa cadastrada no sistema, responsável por realizar ações como autenticação e gestão de denúncias.

- Atributos principais:
- id: identificador único do usuário
- nome: nome completo
- email: utilizado para login
- senha: credencial de acesso
- perfil: tipo de usuário (ADMIN ou USUARIO)

Responsabilidade:

Modelar os dados essenciais de um usuário e fornecer acesso seguro a essas informações através de encapsulamento.

2. Classe Denuncia

A classe Denuncia representa um problema reportado por um usuário, como iluminação pública, buracos, mobilidade urbana, etc.

Atributos principais:

- id
- titulo
- descricao
- categoria
- status (ABERTA, EM_ANDAMENTO ou FECHADA)
- dataCriacao
- usuarioCriador

Responsabilidade:

Estruturar todas as informações referentes a cada denúncia, mantendo seu estado e permitindo sua evolução no sistema.

3. Camada Repository (Persistência)

Os repositórios simulam um banco de dados utilizando listas internas.
Servem para armazenar e consultar objetos.

a) UsuarioRepository

Responsável por:

- salvar usuários
- buscar por e-mail
- listar todos os usuários
- É utilizado para autenticação e cadastro.

b) DenunciaRepository

Responsável por:

- salvar denúncias
- listar todas
- filtrar por status
- buscar por categoria

Garante o armazenamento de todas as denúncias registradas.

4. Camada Service (Regras de Negócio)

Os services aplicam validações e regras antes de acessar os repositórios.

a) UsuarioService

- Funções principais:
- validar e-mail duplicado
- registrar usuários
- autenticar login
- definir perfil (ADMIN ou USUARIO)

b) DenunciaService

- Funções principais:
- criar denúncias corretamente
- validar categorias
- alterar status de denúncias
- garantir dados consistentes antes de salvar

5. AdminApp (Aplicação Principal)

A classe AdminApp demonstra o funcionamento do sistema do ponto de vista do administrador.

Ela exemplifica:

- cadastro de usuários
- cadastro de categorias
- registro de denúncias
- listagem de denúncias
- visualização por status

Serve como simulação prática do fluxo do sistema.

6. Integração das Classes

O fluxo interno opera da seguinte forma:
1. A aplicação (AdminApp) solicita operações aos serviços.
2. Os serviços aplicam validações e regras de negócio.
3. Após validadas, as informações são enviadas aos repositórios.
4. Os repositórios armazenam objetos Usuario e Denuncia.
5. As entidades representam os dados e comportamentos do sistema.

Esse fluxo demonstra:

- encapsulamento
- separação de responsabilidades
- organização por camadas
- uso eficiente dos pilares da Programação Orientada a Objetos

---

Tecnologias Utilizadas

- Java
- Android SDK
- SQLite
- Padrão DAO (Data Access Object)
- MVC simplificado
