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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MapfreCapSteps {

	private WebDriver driver;
	String valor = "10000";

	@Before("@start")
	public void setUp() {
		// Abre o navegador com o Driver Explorer
		System.setProperty("webdriver.ie.driver", "C:/SeleniumDriver/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Abre a pagina da Mapfre
		// driver.get("http://172.20.152.47/mapfrecap//Coreon.Acesso/frmLogin.aspx");/DEVR2
		// Abre a pagina da Mapfre
		driver.get("http://10.206.28.128/mapfrecap/"); // SUS
		System.out.println(driver.getTitle());

	}

	@Given("^que estamos efetuando o login$")
	public void Loga() throws InterruptedException, IOException, AWTException {
		Robot robot = new Robot();
		// driver.quit();

		/*
		 * driver.findElement(By.id("txtLogin_I")).click(); //Clica no campo de Login
		 * driver.findElement(By.id("txtLogin_I")).sendKeys("adm"); //Digita o Login
		 * 
		 * Thread.sleep(500); driver.findElement(By.id("txtSenha_I")).click(); //Clica
		 * no campo Senha //
		 * driver.findElement(By.id("txtSenha_I")).sendKeys("mcap007"); DEVR2 //Digita a
		 * senha driver.findElement(By.id("txtSenha_I")).sendKeys("mapfre01"); // SUS //
		 * Digita a senha
		 * 
		 * Thread.sleep(1000); //Tempo de espera
		 * driver.findElement(By.id("btnEntrar")).click(); //Clica no logar
		 */
		// Abre a tela inicial já logado
		driver.get("http://10.206.28.128/mapfrecap/tkgs_acesso/asp/framelogin.asp?usu=0001&prf=01"); // SUS

		Thread.sleep(1000);
		// Abre a tela de Ficha Cadastral
		driver.get(
				"http://10.206.28.128/mapfrecap//Coreon.Propostas/frmPropostas.aspx?endereco=../../../Coreon.Propostas/frmPropostas.aspx&usu=0001&titulo=Propostas&parametro=&prf=01&mnusel=1600&&"); // SUS

		Thread.sleep(500); // Tempo de espera
		// Seleciona o Canal de Venda (Tradicional - III)
		driver.findElement(By.xpath("//*[@name='ddlCanalVenda']//*[text()='TRADICIONAL - III']")).click();

		Thread.sleep(500); // Tempo de espera
		// Seleciona o Produto (Garantia Fiador)
		driver.findElement(By.xpath("//*[@name='ddlProduto']//*[text()='GARANTIA FIADOR']")).click();

		Thread.sleep(500); // Tempo de espera
		// clica no botão "Selecionar
		driver.findElement(By.id("selecaoSubscritor_btnSelecionar")).click();

		Thread.sleep(500); // Tempo de espera
		// clica no botão "Consultar"
		driver.findElement(By.id("selecaoSubscritor_Pesquisa_btnConsultar")).click();

		/*
		 * Thread.sleep(500); // Tempo de espera // clica na caixa de Texto
		 * driver.findElement(By.id("selecaoSubscritor_Pesquisa_txtSelecao")).click();
		 * 
		 * // escreva na caixa de texto
		 * driver.findElement(By.id("selecaoSubscritor_Pesquisa_txtSelecao")).
		 * sendKeys("MARIA DA GLORIA");
		 * 
		 * // Clica no botão Consulta
		 * driver.findElement(By.id("selecaoSubscritor_Pesquisa_btnConsultar")).click();
		 */

		Thread.sleep(500);
		// Seleciona o cliente
		driver.findElement(By.xpath("//tbody//a[text()='MAPFRE VIDA S/A']")).click();

		Thread.sleep(500);
		// Seleciona a forma de pagamento
		driver.findElement(By.xpath("//*[@name='selecaoSubscritor$ddlFormaPagamento']//*[text() ='Boleto Bancario']"))
				.click();

		Thread.sleep(500);
		// Clica no botão Novo
		driver.findElement(By.id("btnHabilitaPnl")).click();

		Thread.sleep(500);
		// Copiar do Subscritor
		driver.findElement(By.id("btnCopySubscritor")).click();

		Thread.sleep(500);
		// Clica no campo Valor Mensalidade
		driver.findElement(By.id("edtVal_msd_ttr")).click();
		Thread.sleep(200);
		// Informa o valor
		driver.findElement(By.id("edtVal_msd_ttr")).sendKeys(valor);

		Thread.sleep(500);
		// Clica no campo Quantidade Titulos
		driver.findElement(By.id("txtqtd_tit_ttr")).click();
		Thread.sleep(200);
		// Informa a quantidade de Titulos
		driver.findElement(By.id("txtqtd_tit_ttr")).sendKeys("2");

		Thread.sleep(500);
		// Clica no botão "Confirma"
		driver.findElement(By.id("btnConfirmarTitular")).click();

		Thread.sleep(500);
		// Clica no botão "Confirma"
		driver.findElement(By.id("btnGravar")).click();

		// WebDriverWait wait = new WebDriverWait(driver, 20);
		// wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("ddlSituacaoProposta"),
		// "PE"));
		Thread.sleep(3000);
		// Printa a tela após Gravar
		BufferedImage Gravar = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(Gravar, "JPEG", new File("ScreenShot/Cadastro/Gravar.jpg"));

		Thread.sleep(500);
		// Clica no botão Efetivar Venda
		driver.findElement(By.id("btnFinish")).click();

		// //Espera o campo Situação esta como "PENDENTE"
		WebDriverWait wait = new WebDriverWait(driver, 20);// corrigir
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("ddlSituacaoProposta"), "PE"));
		// // Printa a tela após Gravar
		BufferedImage EfetivarVenda = robot
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(EfetivarVenda, "JPEG", new File("ScreenShot/Cadastro/EfetivarVenda.jpg"));

		Thread.sleep(1000);
		// Apresenta mensagem de teste Finalizado
		JOptionPane.showMessageDialog(null, "Teste Concluido!");

		driver.quit();
	}

	// WebDriverWait wait = new WebDriverWait(driver, 10);  
	// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Mensagem")));

}
