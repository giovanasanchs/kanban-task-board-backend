# 📌 Kanban Board - Backend API (Java + Spring Boot)

Este repositório corresponde à API RESTful desenvolvida em **Java com Spring Boot** para o desafio técnico da **Nexum**. Ele oferece todas as funcionalidades necessárias para o gerenciamento de tarefas de um **Kanban Board**, integrando-se com o frontend feito em Vue.js.

> 🛠️ **Foco em boas práticas, arquitetura limpa, segurança, organização e facilidade de integração com o frontend.**

---

## 🔗 Repositório do Frontend

Este projeto possui uma interface frontend desenvolvida em Vue 3.  
Acesse o repositório clicando no link abaixo:

👉 [kanban-task-board-frontend](https://github.com/giovanasanchs/kanban-task-board-frontend)

---

## 🚀 Funcionalidades da API

✅ Criação de tarefas com título, descrição, data de vencimento e status  
✅ Atualização completa ou parcial de tarefas  
✅ Exclusão de tarefas com validação  
✅ Recuperação de tarefas por status  
✅ Atualização de subtarefas (marcar como concluída ou não)  
✅ Integração com frontend via CORS liberado  
✅ API RESTful com respostas padronizadas  
✅ Validações com mensagens claras de erro 

---

## ✨ Funcionalidades Extras Adicionadas

- 🔍 **Busca por título de tarefa** via query param (`/tasks?title=...`)
- 📋 **Organização por status**: A FAZER, EM PROGRESSO, CONCLUÍDO
- 🔄 **CORS habilitado** para integração com o frontend Vue

---

## 🧩 Estrutura da Entidade

A API trabalha com o seguinte modelo de dados:

```json
{
  "id": 1,
  "title": "Estudar Vue.js",
  "description": "Avançar no curso de Vue 3",
  "dueDate": "2024-04-15",
  "status": "A_FAZER",
  "subtasks": [
    { "title": "Assistir aula", "completed": false },
    { "title": "Fazer exercícios", "completed": true }
  ]
}
```
---

## 🔄 Endpoints Principais

### 📄 Tarefas

| Método | Rota                | Descrição                                  |
|--------|---------------------|---------------------------------------------|
| GET    | `/api/tasks`        | Retorna a lista de todas as tarefas         |
| GET    | `/api/tasks/{id}`   | Retorna os dados de uma tarefa específica   |
| POST   | `/api/tasks`        | Cria uma nova tarefa                        |
| PUT    | `/api/tasks/{id}`   | Atualiza todos os dados de uma tarefa       |
| PATCH  | `/api/tasks/{id}`   | Atualiza parcialmente uma tarefa (ex: status ou subtasks) |
| DELETE | `/api/tasks/{id}`   | Exclui uma tarefa existente                 |

### ✅ Subtarefas (incluídas na tarefa principal)

As subtarefas são gerenciadas através do objeto `subtasks` dentro da própria tarefa.  
Elas podem ser marcadas como concluídas ou editadas enviando um `PUT` ou `PATCH` na tarefa principal.

---

🔒 **Todas as rotas estão liberadas por padrão (sem autenticação), com suporte a CORS para integração com o frontend.**

---

## 🧑‍💻 Tecnologias Utilizadas

Este projeto backend foi desenvolvido utilizando tecnologias modernas do ecossistema Java, com foco em desempenho, organização e escalabilidade:

- [Java 17](https://openjdk.org/projects/jdk/17/) – Linguagem principal da aplicação
- [Spring Boot 3](https://spring.io/projects/spring-boot) – Framework para desenvolvimento de aplicações Java modernas
- [Spring Web](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html) – Para construção das APIs REST
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) – Para abstração da camada de persistência com banco de dados
- [Hibernate](https://hibernate.org/) – ORM utilizado pelo Spring Data JPA
- [MySQL](https://www.mysql.com/) – Banco de dados relacional utilizado
- [Lombok](https://projectlombok.org/) – Redução de boilerplate no código Java
- [Maven](https://maven.apache.org/) – Gerenciador de dependências e build do projeto
- [Docker](https://www.docker.com/) – (opcional) Para orquestração do ambiente em produção
- [Spring DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools) – Facilita o desenvolvimento com hot reload

---

## 📂 Organização dos Arquivos

```bash
📁 src
├── 📁 controller     → Endpoints da aplicação
├── 📁 model          → Entidades JPA
├── 📁 repository     → Interfaces do JPA
├── 📁 service        → Lógica de negócio
├── 📁 dto            → Objetos de transferência de dados
├── 📁 config         → Configurações globais (ex: CORS)
├── 📁 exceptions     → Tratamento de erros personalizados
└── KanbanApplication.java  → Classe principal
```

---

## 🧪 Como Executar o Projeto Backend Localmente

Siga os passos abaixo para rodar o backend da aplicação Kanban em sua máquina local:

### ✅ Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/) ou outro banco de dados compatível
- IDE de sua preferência (IntelliJ, VS Code, Eclipse, etc.)

---

### 🚀 Passo a passo

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/kanban-backend.git

# Acesse o diretório do projeto
cd kanban-backend

# Configure o banco de dados no application.properties
# Exemplo:
# spring.datasource.url=jdbc:mysql://localhost:3306/kanban_db
# spring.datasource.username=root
# spring.datasource.password=suasenha

# Rode a aplicação
./mvnw spring-boot:run

# A aplicação estará disponível em:
http://localhost:8080

```
