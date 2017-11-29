@CadastroSimples
Feature: Incluir uma nova proposta para um cliente da base
	
	Percorrer todo os processo para finalizar o status do cliente
	Cadastro; Baixa de Pagamento e Número de Titulo e da Sorte

  @Cadastro
  Scenario Outline: Cadastro de Propostos da Simples para clientes da base
    Given Que estamos com o sistema da MapfreCap aberto
    And efetuamos o login 
    When seguimos o fluxo para iniciar o cadasstro da nova proposta
    Then cadastramos a proposta
    
   
 