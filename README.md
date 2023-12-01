@Marcelo Pieroni Martins

Informações sobre a Automação:

Utilizamos o framework Spring para criar o projeto e gerenciar suas dependências.
Utilizamos o RestAssured na versão 5.3.0 para automatizar os testes.
Como Executar os Testes:

No IntelliJ IDEA:

Importe o projeto no IntelliJ IDEA.
Clique com o botão direito sobre Sicreed_API.Sicred_API_Challenge.
Selecione "Run Test in 'Sicred_API_Challenge'".
Todos os cenários de teste serão executados, e o console do IDE exibirá um relatório detalhado dos cenários que passaram e dos que não passaram.


Planejamento dos Testes:
Elaborei um plano de testes com base nos objetivos do desafio e na documentação disponível da API.
Nossa estratégia de teste foi guiada pela documentação da API do desafio, que incluía um total de 10 requisições, 
abrangendo tanto métodos GET quanto POST.

Para cada uma dessas requisições, realizamos testes do 
fluxo principal, ou seja, verificamos se o comportamento esperado estava sendo atendido. 
Além disso, realizamos testes de requisitos para validar cenários alternativos e excepcionais.


STATUS_API


Cenario 01 "StatusTest": validateStatusAPI
Description("Validar o status da API /TEST")


USERS


Cenario 01 "UserTest": validateUsernamesAndPasswords
Description("Validar que os campos 'username' e 'password' não estão em branco na lista de todos os usuários")

Cenario 02 "UserTest": validateUserListLimitAndNames
Description("Validar o limite de 30 usuários na lista e verificar o primeiro e o último nome na posição 01 e 30")


PRODUCTS


Cenario 01 "ProductsTest": checkListProducts
Description("Validar o limite da lista que é 30 id. Validando o primeiro e o ultimo produto")

Cenario 02 "ProductsTest": testLimit100Products
Description("Valida todos os produtos listado dentro de um for com 100 id e faz uma validação de algum produto com id invalido.")

Cenario 03 "ProductsTest": ValidationOfNewProduct
Description("Validando criação de um produto")
BUG:  Response esperado é 201 - ok
Mas o retorno que chega é 200



AUTHENTICATION


Cenario 01 "AuthenticationTest": validateAuthentication
Description("Validar uma autenticação e validar o retorno do json")
Esperado que status code é 201 - ok
Mas o retorno é 200

Cenario 02 "AuthenticationTest": searchProductAuth
Description("Pesquisar um produto já com token autenticado e faz uma previa validação de conteudo do produto")
Impacta na pre condição de criar uma autenticação:
Esperado que status code é 201 - ok
Mas o retorno é 200
Corrigindo este problema do retorno consegue validar o produto listado.(Se remover a condição de validação
do retorno da autenticação é feito a validação da lista do produto)

Cenario 03 "AuthenticationTest": searchProductAuthTokenInvalid
Description("Pesquisar um produto já com token inválido")

Cenario 04 "AuthenticationTest": searchProductAuthTokenNotValid
Description("Validar o limite da lista que é 30 id. Validando o primeiro e o ultimo produto")
Esperado que status code é 403 
Mas o retorno é 401



Faltou:
Configurar o CI/CD no GitLAB e configurar gerador de relatorio com interface.
