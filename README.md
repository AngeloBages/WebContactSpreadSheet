# Contact Spreadsheet

Este projeto consiste em uma aplicação para gerenciamento de contatos, onde é possível realizar operações básicas como inserir, atualizar, listar e excluir contatos em um banco de dados. Abaixo estão descritas as principais classes e funcionalidades do projeto:

## Estrutura do Projeto
```
target
├── classes
│   ├── com
│   │   └── contactspreadsheet
│   │       ├── dao
│   │       │   ├── implementations
│   │       │   │   ├── ContactDaoImpl.class
│   │       │   │   └── ContactDaoJpaImpl.class
│   │       │   └── interfaces
│   │       │       ├── ContactDao.class
│   │       │       └── Dao.class
│   │       ├── filters
│   │       │   └── ConnectionManagerFilter.class             
│   │       ├── models 
│   │       │   └── Contact.class   
│   │       ├── resources    
│   │       │   └── dbconnection.properties       
│   │       ├── servlets
│   │       │   ├── ListRegisterServlet.class 
│   │       │   ├── RegisterDeleteServlet.class 
│   │       │   ├── RegisterInsertServlet.class 
│   │       │   ├── RegisterUpdateServlet.class 
│   │       │   └── ServletController.class
│   │       └── utils
│   │           ├── ConnectionFactory.class
│   │           ├── JpaUtils.class
│   │           └── ServletAction.class
│   └── META-INF
│       └── persistence.xml
└── m2e-wtp
    └── web-resources
        ├── META-INF
        │   ├── maven
        │   │   └── com.contact-spreadsheet
        │   │       └── contact-spreadsheet
        │   │           ├── pom.properties
        │   │           └── pom.xml
        │   └── MANIFEST.MF
        └── WEB-INF
            ├── javapages
            │   ├── index.jsp
            │   ├── registerInsert.jsp
            │   └── registerUpdate.jsp
            └── web.xml
```

### Model
#### Pacote `com.contactspreadsheet.models`
- **Contact.java**: Representa um contato com atributos como nome, email, endereço e data de nascimento.

### View
- **index.jsp**: Página JSP que exibe a lista de contatos cadastrados e fornece links para inserir, atualizar e excluir contatos.
- **registerInsert.jsp**: Página JSP que exibe um formulário para inserção de um novo contato.
- **registerUpdate.jsp**: Página JSP que permite atualizar os detalhes de um contato.

### Controller
#### Pacote `com.contactspreadsheet.servlets`
- **ListRegisterServlet.java**: Servlet que exibe uma lista de contatos registrados.
- **RegisterDeleteServlet.java**: Servlet que manipula a exclusão de um contato.
- **RegisterInsertServlet.java**: Servlet que manipula a inserção de um novo contato.
- **RegisterUpdateServlet.java**: Servlet que manipula a atualização de um contato existente.
- **ServletController.java**: Servlet controladora que despacha solicitações para outras servlets.

### DAO (Data Access Object)
#### Pacote `com.contactspreadsheet.dao.implementations`
- **ContactDaoImpl.java**: Implementa a interface `ContactDao` para realizar operações CRUD na entidade `Contact` usando JDBC (Java Database Connectivity).
- **ContactDaoJpaImpl.java**: Implementa a interface `ContactDao` para realizar operações CRUD na entidade `Contact` usando JPA (Java Persistence API).

#### Pacote `com.contactspreadsheet.dao.interfaces`
- **ContactDao.java**: Interface que define métodos para operações CRUD na entidade `Contact`.
- **Dao.java**: Interface genérica para operações de acesso a dados, como inserção, consulta, atualização e exclusão.

### Utils
#### Pacote `com.contactspreadsheet.utils`
- **ConnectionManager.java**: Responsável por fornecer uma conexão com o banco de dados MySQL. Ela utiliza um arquivo de propriedades para configurar os parâmetros de conexão.
- **JpaUtils.java**: Responsável por fornecer o objeto `EntityManager` do JPA.
- **ServletAction.java**: Define uma interface que possui métodos para execução de ações nos servlets da aplicação.

#### Pacote `com.contactspreadsheet.filters`
- **ConnectionManagerFilter.java**:  Implementa um filtro para gerenciamento de conexões com o banco de dados. Ela é responsável por abrir uma conexão com o banco de dados antes de passar a requisição para o servlet correspondente e fechar a conexão após o processamento da requisição.

### Arquivos de Configuração
- **pom.xml**: Arquivo de configuração do Maven que define as dependências e configurações do projeto.
- **persistence.xml**: Arquivo de configuração do JPA que define as propriedades de persistência.
- **web.xml**: Arquivo de configuração do web-app, define configurações como welcome-file e mapeamento de servlets.
- **dbconnection.properties**: Arquivo de propriedades que contém informações de configuração para a conexão com o banco de dados.

## Configuração do Banco de Dados

Certifique-se de configurar corretamente o banco de dados MySQL com as seguintes informações:

- URL: jdbc:mysql://localhost:3306/fj21?useSSL=false
- Usuário: root
- Senha: root

## Dependências

O projeto foi contruído usando Maven com as seguintes dependências:

- Hibernate Core: Version 6.5.0.Final
- Jakarta Servlet JSP JSTL API: Version 3.0.0
- Jakarta Servlet JSP JSTL: Version 3.0.0
- MySQL Connector/J: Version 8.0.33


## Tecnologias Utilizadas

- Java
- JSP (JavaServer Pages)
- JSTL (Java Standard Tag Library)
- Servlets
- MySQL
- JPA (Java Persistence API)
- Hibernate
