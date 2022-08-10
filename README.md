# Lojinha WEB Automa��o
Esse � um reposit�rio que cont�m a automa��o de alguns testes Web do software denominado Lojinha. Esse software foi utilizado durante os testes feitos no curso Programa de Testes e Qualidade de Software, ministrado pelo J�lio de Lima.

## Tecnologias utilizadas
- Java
- JUnit
- Selenium WebDriver
- Maven

## Testes automatizados
Testes para validar as parti��es de equiaval�ncia relacionadas ao valor do produto na Lojinha, que est�o vinculados diretamente a regra de neg�cio que diz que o valor do produto deve estar entre R$ 0,01 e R$ 7.000,01.

## Notas gerais
- Sempre utilizamos a anota��o Before Each para abrir no navegador antes de cada teste.
- Utilizamos Page Objects, design pattern utilizado para desenvolver classes de testes com princ�pios da orienta��o a objetos, servindo como interface para interagir com os elementos de uma p�gina web, sendo as classes as p�ginas e os m�todos as a��es que o usu�rio pode fazer na tela.
- Nesse projeto fazemos uso do JUnit 5 o que nos d� a possibilidade de usar a anota��o DisplayName para dar descri��es em portugu�s para nossos testes.
- Utilizei Dotenv para trabalhar com vari�veis sensiveis, como usu�rio e senha do login. Assim, posso versionar o meu c�digo sem passar os valores reais utilizados em cada v�riavel, pois o Dotenv ir� carregar os valores definidos nas variaveis configuradas no arquivo .env e este sendo adicionado no arquivo .gitignore.
## IDE utilizada
- IntelliJ