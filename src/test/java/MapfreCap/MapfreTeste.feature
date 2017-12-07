@CadastroSimples
Feature: Incluir uma nova proposta para um cliente da base
  
  Percorrer todo os processo para finalizar o status do cliente
  Cadastro
  Baixa de Pagamento e Número de Titulo e da Sorte

  @Cadastro
  Scenario: Cadastro de Propostos da Simples para clientes da base
    Given que estamos efetuando o login e abrimos a tela de Cadastro
    When Escolhemos o tipo do Produto
    And informamos os dados do Cliente na Base
    Then Gravamos e efetivamos

  @Processo
  Scenario: Rodar o processo para integração  de dados e atualizações de tabelas.
    Given que efetuei o cadastro de proposta e devera rodar o processo para o sistma atualizar e abrimos a area do Gerenciador
    When Iniciamos o processo do Gerenciador
    And Validamos e Printamos
    Then Clicamos no log e printamos
  
  @Pagamento
  Scenario: Pagamento manual
    Given que o gerenciador de processo foi rodado e estamos na pagina da baixa de Pagamento
