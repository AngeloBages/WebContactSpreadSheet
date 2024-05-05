# Contact Spreadsheet

Este projeto consiste em uma aplicação para gerenciamento de contatos, onde é possível realizar operações básicas como inserir, atualizar, listar e excluir contatos em um banco de dados. Abaixo estão descritas as principais classes e funcionalidades do projeto:

## Estrutura do Projeto
```
main
├── java
│   └── com
│       └── contactspreadsheet
│           ├── dao
│           │   ├── ContactDaoImpl.java
│           │   └── Dao.java
│           ├── main
│           │   ├── ConnectionFactory.java
|           |   └── ServletAction.java 
│           ├── models
│           │   └── Contact.java
│           ├── resources
│           │   └── dbconnection.properties
│           └── servlets
|               ├── MainServlet.java
│               ├── RegisterDeleteServlet.java
│               ├── RegisterInsertServlet.java
│               └── RegisterUpdateServlet.java
└── webapp
    ├── META-INF
    │   └── MANIFEST.MF
    └── WEB-INF
        ├── javapages
        │   ├── index.jsp
        │   ├── registerInsert.jsp
        │   └── registerUpdate.jsp
        ├── lib
        │   ├── jakarta.servlet.jsp.jstl-3.0.0.jar
        │   ├── jakarta.servlet.jsp.jstl-api-3.0.0.jar
        │   └── mysql-connector-j-8.3.0.jar
        └── web.xml
```



## Classes Java

### ConnectionFactory

Esta classe é responsável por fornecer uma conexão com o banco de dados MySQL. Ela utiliza um arquivo de propriedades para configurar os parâmetros de conexão, como URL do banco de dados, usuário e senha.

### Contact

Representa um contato com atributos como nome, email, endereço e data de nascimento.

### Dao

Uma interface genérica para operações de acesso a dados, como inserção, consulta, atualização e exclusão.

### ContactDaoImpl

Implementação da interface `Dao` para a entidade `Contact`, fornecendo métodos para manipulação de dados no banco de dados relacionados aos contatos.


## Servlets Java

### MainServlet

Este servlet é responsável por rotear as requisições GET e POST para os servlets correspondentes, com base no parâmetro `action` da requisição.

### RegisterDeleteServlet

Servlet responsável por receber requisições para deletar um contato do banco de dados.

### RegisterInsertServlet

Servlet responsável por receber requisições para inserir um novo contato no banco de dados.

### RegisterUpdateServlet

Servlet responsável por receber requisições para atualizar um contato no banco de dados.



## Interface ServletAction

Esta interface define métodos para execução de ações nos servlets da aplicação.



## Páginas JSP

### index.jsp

Página JSP que exibe a lista de contatos cadastrados e fornece links para inserir, atualizar e excluir contatos.

### registerInsert.jsp

Página JSP que exibe um formulário para inserção de um novo contato.

### registerUpdate.jsp

Página JSP que exibe um formulário preenchido com os dados de um contato para atualização.


## Configuração do Banco de Dados

Certifique-se de configurar corretamente o banco de dados MySQL com as seguintes informações:

- URL: jdbc:mysql://localhost:3306/fj21?useSSL=false
- Usuário: root
- Senha: root


## Tecnologias Utilizadas

- Java
- JSP (JavaServer Pages)
- JSTL (Java Standard Tag Library)
- Servlets
- MySQL
