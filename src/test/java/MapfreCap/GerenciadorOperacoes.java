package MapfreCap;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GerenciadorOperacoes {
	Cadastro cad = new Cadastro();
	private WebDriver driver;
	String ambiente = cad.ambiente;

	@Before("@Processo")
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

	@Given("^que efetuei o cadastro de proposta e devera rodar o processo para o sistma atualizar e abrimos a area do Gerenciador$")
	public void Loga() {
		String loginDev = "adm";
		String senhaDev = "mcap007";

		String loginSus = "adm";
		String senhaSus = "mapfre2016";
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		try {
			// Abre o Ambiente DEVR2
			if (ambiente.equals("DEVR2")) {
				// Adiciona uma valoriavel JavaScript
				JavascriptExecutor jsDev = (JavascriptExecutor) driver;

				// Escreve o login
				jsDev.executeScript("document.getElementById('txtLogin_I').setAttribute('value', 'adm')");

				// Espera o campo LOGIN estar Preenchido
				WebDriverWait txtLoginDev = new WebDriverWait(driver, 40);
				txtLoginDev.until(ExpectedConditions.textToBePresentInElementValue(By.name("txtLogin"), loginDev));

				// Escreve a senha
				jsDev.executeScript("document.getElementById('txtSenha_I').setAttribute('value', 'mcap007')");

				// Espera o campo SENHA estar Preenchido
				WebDriverWait txtSenhaDev = new WebDriverWait(driver, 40);
				txtSenhaDev.until(ExpectedConditions.textToBePresentInElementValue(By.name("txtSenha"), senhaDev));

				// Clica no Botão Entrar
				driver.findElement(By.name("btnEntrar")).click();

				// Abre a tela "GERENCIADOR DE PROCESSO"
				driver.get(
						"http://172.20.152.47/mapfrecap//processes/frmProcess.aspx?endereco=../../../processes/frmProcess.aspx&usu=0001&titulo=Gerenciador de processos&parametro=&prf=01&mnusel=2805&&");
				// Retorna o titulo da Pagina
				System.out.println(driver.getTitle());

				// Abre o ambiente SUS
			} else if (ambiente.equals("SUS")) {

				// Adiciona uma valoriavel JavaScript
				JavascriptExecutor jsSus = (JavascriptExecutor) driver;

				// Escreve o login
				jsSus.executeScript("document.getElementById('txtLogin_I').setAttribute('value', 'adm')");

				// Espera o campo LOGIN estar Preenchido
				WebDriverWait txtLoginSus = new WebDriverWait(driver, 40);
				txtLoginSus.until(ExpectedConditions.textToBePresentInElementValue(By.name("txtLogin"), loginSus));

				// Escreve a senha
				jsSus.executeScript("document.getElementById('txtSenha_I').setAttribute('value', 'mapfre2016')");

				// Espera o campo SENHA estar Preenchido
				WebDriverWait txtSenhaSus = new WebDriverWait(driver, 40);
				txtSenhaSus.until(ExpectedConditions.textToBePresentInElementValue(By.name("txtSenha"), senhaSus));

				// Clica no Botão Entrar
				driver.findElement(By.name("btnEntrar")).click();

				// Abre a tela "GERENCIADOR DE PROCESSO"
				driver.get(
						"http://10.206.28.128/mapfrecap//processes/frmProcess.aspx?endereco=../../../processes/frmProcess.aspx&usu=0029&titulo=Gerenciador de processos&parametro=&prf=01&mnusel=2805&&");
				// Retorna o Titulo da Pagina
				System.out.println(driver.getTitle());
			}
		} catch (StaleElementReferenceException e) {
			JOptionPane.showMessageDialog(null, "Erro de Execução");
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@When("^Iniciamos o processo do Gerenciador$")
	public void Processo() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		try {
			// Clina no botão "Iniciar novo Processo"
			WebElement novoProcesso = driver.findElement(By.id("btnExecutar"));
			novoProcesso.click();

			// Seleciona o processo " Integração Geral - Operações"
			driver.findElement(
					By.xpath("//tbody//*[@id='divStartProcessTree']//*[text()='Integração Geral – Operações']"))
					.click();

			// Clica no botão Concluir
			WebElement concluirProcesso = driver.findElement(By.id("btnConcluir"));
			concluirProcesso.click();

		} catch (NoSuchElementException e) {
			JOptionPane.showMessageDialog(null, "Falha no Servidor");
			driver.quit();
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@And("^Validamos e Printamos$")
	public void validacao() throws InterruptedException, IOException, AWTException {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Robot robot = new Robot();
		String mensagem = "Não existem processos em execução.";

		// //Espera apresentar a mensagem "Não existem processos em execução."
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("msgRunning"), mensagem));

		// Printa tela
		BufferedImage GerenProcesso = robot
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(GerenProcesso, "png", new File("ScreenShot/Gerenciador_de_Processo/GerenProcesso.png"));

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Then("^Clicamos no log e printamos$")
	public void log() {

		JOptionPane.showMessageDialog(null, "Teste Concluido!");
		driver.quit();
	}
}
