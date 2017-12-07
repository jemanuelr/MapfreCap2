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
		//driver.quit();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Given("^que estamos efetuando o login e abrimos a tela de Cadastro$")
	public void Loga() {
		String loginDev = "adm";
		String senhaDev = "mcap007";

		String loginSus = "adm";
		String senhaSus = "mapfre2016";

		WebDriverWait wait = new WebDriverWait(driver, 40);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		try {
			// Abre o Ambiente DEVR2
			if (ambiente.equals("DEVR2")) {

				// Escreve o login
				js.executeScript("document.getElementById('txtLogin_I').setAttribute('value', 'adm')");

				// Espera o campo LOGIN estar Preenchido
				wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("txtLogin"), loginDev));

				// Escreve a senha
				js.executeScript("document.getElementById('txtSenha_I').setAttribute('value', 'mcap007')");

				// Espera o campo SENHA estar Preenchido
				wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("txtSenha"), senhaDev));

				// Clica no Botão Entrar
				driver.findElement(By.name("btnEntrar")).click();

				// Abre a tela "FICHA DE CADASTRO"
				driver.get(
						"http://172.20.152.47/mapfrecap//Coreon.Propostas/frmPropostas.aspx?endereco=../../../Coreon.Propostas/frmPropostas.aspx&usu=0001&titulo=Propostas&parametro=&prf=01&mnusel=1600&&");
				// Retorna o titulo da Pagina
				System.out.println(driver.getTitle());

				// Abre o ambiente SUS
			} else if (ambiente.equals("SUS")) {

				// Escreve o login
				js.executeScript("document.getElementById('txtLogin_I').setAttribute('value', 'adm')");

				// Espera o campo LOGIN estar Preenchido
				wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("txtLogin"), loginSus));

				// Escreve a senha
				js.executeScript("document.getElementById('txtSenha_I').setAttribute('value', 'mapfre2016')");

				// Espera o campo SENHA estar Preenchido
				wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("txtSenha"), senhaSus));

				// Clica no Botão Entrar
				driver.findElement(By.name("btn Entrar")).click();

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

		WebDriverWait wait = new WebDriverWait(driver, 40);

		// Tempo padrtão de espera
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		// Pega o campo Canal de Venda
		WebElement elementCanalVenda = driver.findElement(By.name("ddlCanalVenda"));
		// Seleciona o campo Canal de Venda
		Select comboVenda = new Select(elementCanalVenda);
		// Seleciona o Canal de Venda (Tradicional - III)
		comboVenda.selectByVisibleText(nomeCanalVenda);

		// Espera o campo CANAL DE VENDA ser preenchido com Tradicional III
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.name("ddlCanalVenda"), nomeCanalVenda));

		// Pega o campo Produto
		WebElement elementProduto = driver.findElement(By.id("ddlProduto"));
		// Seleciona o campo Produto
		Select comboProduto = new Select(elementProduto);
		// Seleciona o Produto (Garantia Fiador)
		comboProduto.selectByVisibleText(nomeProduto);

		// desabilita o tempo padrão
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@And("^informamos os dados do Cliente na Base$")
	public void Subscritor() throws InterruptedException {
		String nomeCliente = "MARIA DA GLORIA";
		String nomeProduto = "GARANTIA FIADOR";
		String valorMensalidade = "10000";
		String quantidadeTitulo = "2";
		String valorFormaPagamento = "3";

		WebDriverWait wait = new WebDriverWait(driver, 40);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		// Espera o campo PRODUTO ser preenchido com Garantia Fiador
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.name("ddlProduto"), nomeProduto));

		// clica no botão "SELECIONAR"
		WebElement selecionarSubscritor = driver.findElement(By.name("selecaoSubscritor$btnSelecionar"));
		selecionarSubscritor.click();

		// Clica no campo SELEÇÃO
		WebElement selecaoCliente = driver.findElement(By.name("selecaoSubscritor$Pesquisa$txtSelecao"));
		selecaoCliente.click();

		Thread.sleep(500);
		// Escreve o nome do cliesnte
		js.executeScript("$('#selecaoSubscritor_Pesquisa_txtSelecao').val('" + nomeCliente + "')");

		// Espera o campo SELEÇÂO ser preenchido com o nome do cliente
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("selecaoSubscritor$Pesquisa$txtSelecao"),
				nomeCliente));

		// Clica no botão "CONSULTAR"
		WebElement consultarPesquisaPessoa = driver.findElement(By.name("selecaoSubscritor$Pesquisa$btnConsultar"));
		consultarPesquisaPessoa.click();

		Thread.sleep(200);
		// Seleciona o cliente procurado
		WebElement clienteLinkPesquisaPessoa = driver.findElement(By.linkText(nomeCliente));
		clienteLinkPesquisaPessoa.click();

		// Espera o campo NOME ser selecionado com o cliente
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("selecaoSubscritor$edtNome"), nomeCliente));

		// captura o campo FORMA DE PAGAMENTO
		WebElement formaPagamentoSubscritor = driver.findElement(By.name("selecaoSubscritor$ddlFormaPagamento"));
		// Seleciona o campo FORMA DE PAGAMENTO
		Select comboFormaPagamento = new Select(formaPagamentoSubscritor);
		// Seleciona "BOLETO BANCARIO"
		comboFormaPagamento.selectByValue(valorFormaPagamento);

		// Esperar a forma de pagamento ser informada
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("selecaoSubscritor$ddlFormaPagamento"),
				valorFormaPagamento));

		// Clica no botão NOVO
		WebElement novoTitulares = driver.findElement(By.id("btnHabilitaPnl"));
		novoTitulares.click();

		Thread.sleep(100);
		// Copia do Subscritor
		WebElement copiarSubscritor = driver.findElement(By.id("btnCopySubscritor"));
		copiarSubscritor.click();

		// Espera o campo NOME estar Preenchido
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("selecaoSubscritor$edtNome"), nomeCliente));

		// Informa o VALOR
		js.executeScript("document.getElementById('edtVal_msd_ttr').setAttribute('value', " + valorMensalidade + ")");

		// Espera o campo VALOR MENSALIDADE ser preenchido
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.name("edtVal_msd_ttr"), valorMensalidade));

		// Informa a quantidade de Titulos
		js.executeScript("document.getElementById('txtqtd_tit_ttr').setAttribute('value', " + quantidadeTitulo + ")");

		// Clica no botão "Confirma"
		WebElement confirmarTitularSelec = driver.findElement(By.name("btnConfirmarTitular"));
		confirmarTitularSelec.click();

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Then("^Gravamos e efetivamos$")
	public void efetivar() throws AWTException, InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		WebDriverWait wait = new WebDriverWait(driver, 40);

		String nomeCliente = "MARIA DA GLORIA";

		Robot robot = new Robot();
		BufferedImage sc = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		//
		// // Espera o cliente ser "cadastrado"
		// wait.until(ExpectedConditions.textToBePresentInElementLocated(By.name("grdTitulares_ctl02_hdnNomPessGrid"),
		// nomeCliente));
		//
		Thread.sleep(1000);
		// Clica no botão "GRAVAR"
		WebElement gravarCadastro = driver.findElement(By.name("btnGravar"));
		gravarCadastro.click();

		// Printa a tela após Gravar
		ImageIO.write(sc, "png", new File("ScreenShot/Cadastro/Gravar.png"));

		Thread.sleep(1000);
		// Clica no botão Efetivar Venda
		WebElement efetivarVendaCadastro = driver.findElement(By.name("btnFinish"));
		efetivarVendaCadastro.click();

		// //Espera o campo Situação esta como "PENDENTE"
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("ddlSituacaoProposta"), "PE"));

		// // Printa a tela após Efetivar Venda
		ImageIO.write(sc, "png", new File("ScreenShot/Cadastro/EfetivarVenda.png"));

		Thread.sleep(500);
		// Apresenta mensagem de teste Finalizado
		JOptionPane.showMessageDialog(null, "Teste Concluido!");

		// Fecha o Browse e Elimina as instancias de Drives
		driver.quit();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
}
