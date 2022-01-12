# desafio-e2e-li-loja

#### Visão Geral

   O projeto visa a automação dos testes para o desafio da loja integrada    

#### Requisitos

* [Intellij](https://www.jetbrains.com/pt-br/idea/download/#section=linux)
* [Java 8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
* [Maven](https://mvnrepository.com/)


#### Preparação para rodar os teste

Adicionar no arquivo `Configuration.properties` o Email e a senha nos campos:
 - loginEmail
 - loginSenha


#### Rodarprojeto

Na pasta do projeto digitar no terminal o comando:

```
mvn clean install
```
O sistema irá baixar as dependências do projeto e irá rodar os testes.

#### Próxima etapa

- Adicionar os testes para rodar no firefox
- Adicionar mais cenários
- Otimizar o tempo de execução dos teste
