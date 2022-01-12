Feature: Cupom de descontos
  Validação da Alteração que adiciona cupons de descontos no carrinho da loja.

  Scenario Outline: Adicionar cupom válido ao produto no carrinho
    Given seleciono o produto "<nomeProduto>" com valor de "<precoProduto>"
    And que aciono a opção "Comprar"
    And no campo cupom de desconto preencho com o cupom válido "<NomeCupom>"
    When clicar no botão "Usar cupom"
    Then o sistema deve apresentar a tag de "<NomeCupom>" no carrinho
    And aplica a informação "<DescricaoDesconto>" na coluna ao lado da tag de cupom
    And o valor total de "<ValorFinal>"

    Examples:
      | nomeProduto                                 | precoProduto | NomeCupom    | DescricaoDesconto                      | ValorFinal |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | FRETEGRATIS  | Frete Grátis                           | R$ 80,00   |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | 10OFF        | Desconto: 10 %(frete não incluso)      | R$ 72,00   |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | 30REAIS      | Desconto: R$ 30,00(frete não incluso)  | R$ 50,00   |


  Scenario Outline: Validar mensagem de erro quando adiciona cupom inválido ao carrinho.
    Given seleciono o produto "<nomeProduto>" com valor de "<precoProduto>"
    And que aciono a opção "Comprar"
    And no campo Cupom de desconto for preencho com o cupom inválido "<NomeCupom>"
    When clicar no botão "Usar cupom"
    Then sistema não apresenta a tag referente ao cupom "<NomeCupom>" no carrinho
    And não aplica o cupom apresentando mensagem de erro "Cupom não encontrado.".

    Examples:
      | nomeProduto                                 | precoProduto | NomeCupom   |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | FRETETESTE  |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | 10          |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | #30ABC      |


  Scenario Outline: Finalizar compra após inserir cupom
    Given seleciono o produto "<nomeProduto>" com valor de "<precoProduto>"
    And que aciono a opção "Comprar"
    And no campo cupom de desconto preencho com o cupom válido "<NomeCupom>"
    And clicar no botão "Usar cupom"
    When clicar no botão "Finalizar compra"
    And etapa de opção de pagamento selecionar "boleto"
    And clicar em "Finalizar compra"
    Then sistema gera  número do pedido
    And apresenta situacao "Aguardando pagamento"
    And o cupom utilizado "<NomeCupom>"
    And o desconto "<DescontAplicado>" aplicado

    Examples:
      | nomeProduto                                 | precoProduto | NomeCupom   | DescontAplicado |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | FRETEGRATIS | R$ 80,00        |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | 10OFF       | R$ -8,00        |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | 30REAIS     | R$ -30,00       |


  Scenario: Finalizar compra sem inserir cupom
    Given seleciono o produto "[CATEGORIA] Produto com categoria - 1 Nível" com valor de "R$ 80,00"
    And que aciono a opção "Comprar"
    And não preencho o campo cupom de desconto com um cupom
    When clicar no botão "Finalizar compra"
    And etapa de opção de pagamento selecionar "boleto"
    And clicar em "Finalizar compra"
    Then sistema gera  número do pedido
    And apresenta situacao "Aguardando pagamento"
    And não apresenta a tag cupom


  Scenario Outline: Adicionar cupom de desconto em produto que já possui desconto
    Given seleciono o produto "<nomeProduto>" de "R$ 40,00", "20"% de desconto do valor de "R$ 50,00"
    And que aciono a opção "Comprar"
    And no campo cupom de desconto preencho com o cupom válido "<NomeCupom>"
    When clicar no botão "Usar cupom"
    Then aplica a informação "<DescricaoDesconto>" na coluna ao lado da tag de cupom
    And o valor total de "<ValorFinal>"

    Examples:
      | nomeProduto                                            | NomeCupom    | DescricaoDesconto                     | ValorFinal |
      | [PREÇO] Produto com preço promocional e preço de venda | FRETEGRATIS  | Frete Grátis                          | R$ 40,00   |
      | [PREÇO] Produto com preço promocional e preço de venda | 10OFF        | Desconto: 10 %(frete não incluso)     | R$ 36,00   |
      | [PREÇO] Produto com preço promocional e preço de venda | 30REAIS      | Desconto: R$ 30,00(frete não incluso) | R$ 10,00   |


# Para executar este cenário de forma correta torna-se necessário:
  # - Um cupom com restrição de valor minimo no carrinho
  # - Saber qual é a mensagem de restrição do cupom para ser adicionada no ultimo And
  Scenario Outline: Validar restrição de uso dos cupom para valor mínimo do carrinho
    Given seleciono o produto "<nomeProduto>" com valor de "<precoProduto>"
    And que aciono a opção "Comprar"
    And no campo cupom de desconto preencho com o cupom válido "<NomeCupom>" que possui restrição de valor minimo do carrinho
    When clicar no botão "Usar cupom"
    Then sistema não apresenta a tag referente ao cupom "<NomeCupom>" no carrinho
#    And não aplica o cupom apresentando mensagem de erro "NECESSARIO_SABER_MENSAGEM_ERRO_RESTRICAO_CUPOM".

    Examples:
      | nomeProduto                                 | precoProduto | NomeCupom                       |
      | [CATEGORIA] Produto com categoria - 2 Nível | R$ 17,50     | CUPOMRESTRICAO100REAIS          |


# Para executar este cenário torna-se necessário:
  # - Um cupom com status venvido
  # - Saber qual é a mensagem de vencimento
# Scenario Outline: Validar  cupom vencido
#    Given seleciono o produto "<nomeProduto>" com valor de "<precoProduto>"
#    And que aciono a opção "Comprar"
#    And no campo cupom de desconto preencho com o cupom vencido "<NomeCupom>"
#    When clicar no botão "Usar cupom"
#    Then o sistema deve apresentar a tag de "<NomeCupom>" no carrinho
#    And apresentar mensagem de cupom de vencido.
#
#    Examples:
#      | nomeProduto                                 | precoProduto | NomeCupom       |
#      | [CATEGORIA] Produto com categoria - 2 Nível | R$ 17,50     | FRETEVENCIDO    |
#      | [CATEGORIA] Produto com categoria - 2 Nível | R$ 17,50     | 20OFFVENCIDO    |
#      | [CATEGORIA] Produto com categoria - 2 Nível | R$ 17,50     | #50REAISVENCIDO |


  Scenario Outline: Adicionar cupom para o valor total da compra
    Given seleciono o produto "<nomeProduto>" com valor de "<precoProduto>"
    And que aciono a opção "Comprar"
    And no adicione a quantidade de "<quantidade>" para o segundo produto
    And no campo cupom de desconto preencho com o cupom válido "<NomeCupom>"
    When clicar no botão "Usar cupom"
    Then o sistema deve apresentar a tag de "<NomeCupom>" no carrinho
    And aplica a informação "<DescricaoDesconto>" na coluna ao lado da tag de cupom
    And o valor total de "<ValorFinal>"

    Examples:
      | nomeProduto                                 | precoProduto | quantidade  | NomeCupom    | DescricaoDesconto                      | ValorFinal |
      | [CATEGORIA] Produto com categoria - 1 Nível | R$ 80,00     | 2           | 10OFF        | Desconto: 10 %(frete não incluso)      | R$ 144,00  |
      | [CATEGORIA] Produto com categoria - 2 Nível | R$ 17,50     | 3           | 30REAIS      | Desconto: R$ 30,00(frete não incluso)  | R$ 22,50   |

