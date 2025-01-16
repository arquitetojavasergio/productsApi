# Products API

**Products API** é um projeto desenvolvido em **Spring Boot** com o objetivo de fornecer uma API REST para gerenciamento de produtos. O projeto utiliza diversas tecnologias para assegurar robustez, facilidade de uso e integração eficiente.

---

## 🚀 Tecnologias Utilizadas

### 1. **Spring Boot**
- Framework para criação de aplicações Java de forma rápida e produtiva.
- Permite configurar o projeto com mínima configuração inicial.
- [Documentação Oficial](https://spring.io/projects/spring-boot)

---

### 2. **Spring Data JPA**
- Abstração para acesso a dados baseada no JPA (Java Persistence API).
- Facilita a implementação de repositórios com consultas automáticas.
- [Documentação Oficial](https://spring.io/projects/spring-data-jpa)

---

### 3. **ModelMapper**
- Biblioteca para mapeamento automático entre objetos.
- Simplifica a conversão entre entidades e DTOs (Data Transfer Objects).
- [Documentação Oficial](http://modelmapper.org/)

---

### 4. **API REST**
- Arquitetura que permite a comunicação entre sistemas via HTTP.
- Utilizada para expor os endpoints do sistema para gerenciamento de produtos.
- [RESTful API](https://restfulapi.net/)

---

### 5. **Spring Validation**
- Módulo do Spring para validação de dados.
- Permite implementar validações personalizadas e automáticas em entidades e DTOs.
- [Documentação Oficial](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validation)

---

### 6. **Error Handling**
- Implementação de mecanismos para tratamento de erros na API.
- Garante respostas claras e padronizadas para os clientes da API.
- [Documentação Oficial sobre Exception Handling no Spring](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.developing-web-applications.spring-mvc.error-handling)

---

### 7. **RabbitMQ**
- Sistema de mensagens para comunicação assíncrona entre serviços.
- Utilizado no projeto para gerenciar filas de mensagens relacionadas a eventos de produtos.
- [Documentação Oficial](https://www.rabbitmq.com/documentation.html)

---

### 8. **Docker**
- Plataforma para criação e gerenciamento de containers.
- Facilita a execução do projeto em qualquer ambiente com consistência.
- [Documentação Oficial](https://www.docker.com/)

---

## 🛠️ Funcionalidades Principais

- Cadastro, consulta, atualização e exclusão de produtos (CRUD).
- Validação de dados na entrada de requisições.
- Integração com RabbitMQ para processamento de eventos.
- Tratamento padronizado de erros e respostas consistentes.
