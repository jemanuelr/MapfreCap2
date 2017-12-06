package MapfreCap;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Cadastro {

	private WebDriver driver;
	String ambiente = "DEVR2";

	@Before("@Cadastro")
	public void setUp() {
		// Abre o navegador com o Driver Explorer
		System.setProperty("webdriver.ie.driver", "C:/SeleniumDriver/IEDriverServer.exe");
		// Instancia o Driver Explorer
		driver = new InternetExplorerDriver();
		// Maximiza a tela
		driver.manage().window().maximize();
		// Abre o Ambiente DEVR2
		if (ambiente.equals("DEVR2")) {
			driver.get("http://172.20.152.47/mapfrecap//Coreon.Acesso/frmLogin.aspx"); // DEVR2
			// Abre o ambiente SUS
		} else if (ambiente.equals("SUS")) {
			driver.get("http://10.206.28.128/mapfrecap/");
		}
		// Retorna o titulo da Pagina
		System.out.println(driver.getTitle());
		// driver.quit();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Given("^que estamos efetuando o login e abrimos a tela de Cadastro$")
	public void Loga() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		try {
			// Abre o Ambiente DEVR2
			if (ambiente.equals("DEVR2")) {
				// Adiciona uma valoriavel JavaScript
				JavascriptExecutor jsDev = (JavascriptExecutor) driver;
				// Escreve o login
				jsDev.executeScript("document.getElementById('txtLogin_I').setAttribute('value', 'adm')");
				// Escreve a senha
				jsDev.executeScript("document.getElementById('txtSenha_I').setAttribute('value', 'mcap007')");
				// Clica no Botão Entrar
				Thread.sleep(1000);
				driver.findElement(By.name("btnEntrar")).click();
				// Abre a tela "FICHA DE CADASTRO"
				driver.get(
						"http://172.20.152.47/mapfrecap//Coreon.Propostas/frmPropostas.aspx?endereco=../../../Coreon.Propostas/frmPropostas.aspx&usu=0001&titulo=Propostas&parametro=&prf=01&mnusel=1600&&");
				// Retorna o titulo da Pagina
				System.out.println(driver.getTitle());

				// Abre o ambiente SUS
			} else if (ambiente.equals("SUS")) {
				// Adiciona uma valoriavel JavaScript
				JavascriptExecutor jsSus = (JavascriptExecutor) driver;
				// Escreve o login
				jsSus.executeScript("document.getElementById('txtLogin_I').setAttribute('value', 'adm')");
				// Escreve a senha
				jsSus.executeScript("document.getElementById('txtSenha_I').setAttribute('value', 'mcap007')");
				// Clica no Botão Entrar
				driver.findElement(By.name("btnEntrar")).click();
				// Abre a tela "FICHA DE CADASTRO"
				driver.get(
						"http://10.206.28.128/mapfrecap//Coreon.Propostas/frmPropostas.aspx?endereco=../../../Coreon.Propostas/frmPropostas.aspx&usu=0001&titulo=Propostas&parametro=&prf=01&mnusel=1600&&");
				// Retorna o Titulo da Pagina
				System.out.println(driver.getTitle());
			}
		} catch (StaleElementReferenceException e) {
			JOptionPane.showMessageDialog(null, "Erro de Execução");
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@When("^Escolhemos o tipo do Produto$")
	public void Cadastro() throws InterruptedException {
		String nomeCanalVenda = "TRADICIONAL - III";
		String nomeProduto = "GARANTIA FIADOR";

		// Tempo padrtão de espera
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		// Pega o campo Canal de Venda
		WebElement elementCanalVenda = driver.findElement(By.name("ddlCanalVenda"));
		// Seleciona o campo Canal de Venda
		Select comboVenda = new Select(elementCanalVenda);
		// Seleciona o Canal de Venda (Tradicional - III)
		comboVenda.selectByVisibleText(nomeCanalVenda);
		// Verifica se o campo foi selecionado

		Thread.sleep(1000);
		// Pega o campo Produto
		WebElement elementProduto = driver.findElement(By.id("ddlProduto"));
		// Seleciona o campo Produto
		Select comboProduto = new Select(elementProduto);
		// Seleciona o Produto (Garantia Fiador)
		comboProduto.selectByVisibleText(nomeProduto);
		// Verifica se a opção foi selecionada

		// desabilita o tempo padrão
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@And("^informamos os dados do Cliente na Base$")
	public void Subscritor() throws InterruptedException {
		String nomeCliente = "MARIA DA GLORIA";
		String valor = "10000";
		String quantidadeTitulo = "2";
		String valorFormaPagamento = "3";

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		Thread.sleep(500);
		// clica no botão "SELECIONAR"
		WebElement selecionarSubscritor = driver.findElement(By.name("selecaoSubscritor$btnSelecionar"));
		selecionarSubscritor.click();

		WebElement selecaoCliente = driver.findElement(By.name("selecaoSubscritor$Pesquisa$txtSelecao"));
		selecaoCliente.click();

		Thread.sleep(500);
		// Escreve o nome do cliesnte
		JavascriptExecutor nomePesquisaPessoa = (JavascriptExecutor) driver;
		nomePesquisaPessoa.executeScript("$('#selecaoSubscritor_Pesquisa_txtSelecao').val('" + nomeCliente + "')");

		// Clica no botão "CONSULTAR"
		WebElement consultarPesquisaPessoa = driver.findElement(By.name("selecaoSubscritor$Pesquisa$btnConsultar"));
		consultarPesquisaPessoa.click();

		// Seleciona o cliente procurado
		WebElement clienteLinkPesquisaPessoa = driver.findElement(By.linkText(nomeCliente));
		clienteLinkPesquisaPessoa.click();

		// Seleciona a forma de pagamento

		WebElement formaPagamentoSubscritor = driver.findElement(By.name("selecaoSubscritor$ddlFormaPagamento"));
		// Seleciona o campo FORMA DE PAGAMENTO
		Select comboFormaPagamento = new Select(formaPagamentoSubscritor);
		// Seleciona "BOLETO BANCARIO"
		comboFormaPagamento.selectByValue(valorFormaPagamento);

		// Clica no botão NOVO
		WebElement novoTitulares = driver.findElement(By.id("btnHabilitaPnl"));
		novoTitulares.click();

		// Copia do Subscritor
		WebElement copiarSubscritor = driver.findElement(By.id("btnCopySubscritor"));
		copiarSubscritor.click();

		// Clica no campo Valor Mensalidade
		JavascriptExecutor valorMensalidade = (JavascriptExecutor) driver;
		// Informa o VALOR
		valorMensalidade
				.executeScript("document.getElementById('edtVal_msd_ttr').setAttribute('value', " + valor + ")");

		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("selecaoSubscritor$edtNome"), nomeCliente));

		// Clica no campo QUANTIDADE TITULOS
		JavascriptExecutor quantTitulo = (JavascriptExecutor) driver;
		// Informa a quantidade de Titulos
		quantTitulo.executeScript(
				"document.getElementById('txtqtd_tit_ttr').setAttribute('value', " + quantidadeTitulo + ")");

		// Clica no botão "Confirma"
		WebElement confirmarTitularSelec = driver.findElement(By.name("btnConfirmarTitular"));
		confirmarTitularSelec.click();

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Then("^Gravamos e efetivamos$")
	public void efetivar() throws AWTException, InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Robot robot = new Robot();
		Thread.sleep(500);
		// Clica no botão "GRAVAR"
		WebElement gravarCadastro = driver.findElement(By.name("btnGravar"));
		gravarCadastro.click();

		// Printa a tela após Gravar
		BufferedImage Gravar = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(Gravar, "png", new File("ScreenShot/Cadastro/Gravar.png"));

		// Clica no botão Efetivar Venda
		WebElement efetivarVendaCadastro = driver.findElement(By.name("btnFinish"));
		efetivarVendaCadastro.click();

		// //Espera o campo Situação esta como "PENDENTE"
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("ddlSituacaoProposta"), "PE"));

		// // Printa a tela após Efetivar Venda
		BufferedImage EfetivarVenda = robot
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(EfetivarVenda, "png", new File("ScreenShot/Cadastro/EfetivarVenda.png"));

		// Apresenta mensagem de teste Finalizado
		JOptionPane.showMessageDialog(null, "Teste Concluido!");

		// Fecha o Browse e Elimina as instancias de Drives
		driver.quit();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
}
