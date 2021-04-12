<h1 align="center">
    <img alt="Desafio Financeiro e Controladoria" title="#Desafio Financeiro e Controladoria" src="banner.png" />
</h1>

## 💻 Sobre o projeto
API REST para controlar receitas e despesas de clientes sejam eles pessoas físicas ou jurídicas.

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- <a href="https://spring.io/projects/spring-boot">Spring Boot</a>
- <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-devtools">DevTools</a>
- <a href="https://maven.apache.org">Maven</a>
- <a href="https://hibernate.org">Hibernate</a>
- <a href="https://www.h2database.com/html/main.html">H2 Database</a>
- <a href="https://docs.jboss.org/hibernate/orm/5.4/topical/html_single/metamodelgen/MetamodelGenerator.html">JPA Metamodel</a>
- <a href="https://www.oracle.com/index.html">Oracle Database 11g Express Edition</a>
- <a href="https://junit.org/junit5/">Junit</a>
- <a href="https://commons.apache.org/proper/commons-lang/">Apache Commons Lang</a>
- <a href="https://junit.org/junit5/">Postman</a>
- <a href="https://www.google.com/intl/pt-BR/chrome/">Google Chrome</a>


## 🚀 Como executar o projeto

Usando o Prompt de Comando, entrar em "..\Desafio-celula-financeiro-e-controladoria\target" e executar o comando:

```bash
java -jar Desafio-celula-financeiro-e-controladoria-0.0.1-SNAPSHOT.jar
```
E esperar a aplicação subir em http://localhost:8080.

A aplicação possui dois perfis:
- dev: (desenvolvimento) Onde é utilizado o banco de dados H2. E mais algumas configurações para auxiliar no desenvolvimento.
- prod: (produção) Onde é utilizado o banco de dados Oracle Express 11g.

Para alternar entre os ambientes, basta adicionar um dos comandos abaixo junto ao comando anterior. Por padrão, o perfil é "dev".
- Perfil "dev":
```bash
--spring.profiles.active=dev
```

- Perfil "prod":
```bash
--spring.profiles.active=prod
```

A seguir as configurações dos respectivos bancos de dados.
- H2
```bash
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.datasource.url=jdbc:h2:file:~/test
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
```

- Oracle
```bash
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=SYSTEM
spring.datasource.password=12345678
```

- IMPORTANTE!!!
Após executar a aplicação em perfil "prod" pela primeira vez, em "application-prod.properties", mudar a configuração abaixo para "update".
```bash
spring.jpa.hibernate.ddl-auto=create
```
## ⚙️ Endpoints

Os endpoints podem ser consultados no link da documentação :point_right: https://documenter.getpostman.com/view/7857036/TzJoE1Bn

## 📋 Informações Extras
Durante o desenvolvimento foi utilizado alguns padrões como "Observer", "Dependency Injection" e "Repository". 
Também foi dada atenção ao uso dos verbos HTTP e aos códigos de respostas.

- Por enquanto, não foi possível a criação do PL/SQL, por esse motivo a migração inicial de dados é feita através do SQL padrão.

## 🎁 Comentários Finais
Esse projeto não está 100% fiel ao que foi solicitado no desafio, mas o que foi feito aqui foi feito com o máximo de dedicação e empenho.
Desde já, agradeço a MV pela confiança e pela oportunidade.


