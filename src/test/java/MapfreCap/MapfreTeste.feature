@CadastroSimples
Feature: Incluir uma nova proposta para um cliente da base
  
  Percorrer todo os processo para finalizar o status do cliente
  Cadastro
  Baixa de Pagamento e Número de Titulo e da Sorte

  @start
  Scenario: Cadastro de Propostos da Simples para clientes da base
    Given que estamos efetuando o login
    When abrimos a tela Ficha de Cadastro
    And seguimos o fluxo para iniciar o cadasstro da nova proposta
    Then cadastramos a proposta

  @Processo
  Scenario: Rodar o processo para integração  de dados e atualizações de tabelas.
    Given que efetuei o cadastro de proposta e devera rodar o processo para o sistma atualizar
    When sera roda o processo de  Integração Geral - Operações
    And dasdasd
    Then csadasdsa
