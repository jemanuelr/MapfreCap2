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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		// driver.quit();
	}

	@Given("^que efetuei o cadastro de proposta e devera rodar o processo para o sistma atualizar e abrimos a area do Gerenciador$")
	public void Caminho() throws InterruptedException, AWTException, IOException, NoSuchElementException {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		// Abre o Ambiente DEVR2
		if (ambiente.equals("DEVR2")) {
			// Pagina Principal
			driver.get("http://172.20.152.47/mapfrecap//tkgs_acesso/asp/framelogin.asp?usu=0001&prf=01");
			System.out.println(driver.getTitle());

			// Area de Gerenciador de Processos
			driver.get(
					"http://172.20.152.47/mapfrecap//processes/frmProcess.aspx?endereco=../../../processes/frmProcess.aspx&usu=0001&titulo=Gerenciador de processos&parametro=&prf=01&mnusel=2805&&");
			System.out.println(driver.getTitle());

			// Abre o ambiente SUS
		} else if (ambiente.equals("SUS")) {
			// Pagina Principal
			driver.get("http://10.206.28.128/mapfrecap/tkgs_acesso/asp/framelogin.asp?usu=0001&prf=01");
			System.out.println(driver.getTitle());

			// Area de Gerenciador de Processos
			driver.get(
					"http://10.206.28.128/mapfrecap//processes/frmProcess.aspx?endereco=../../../processes/frmProcess.aspx&usu=0001&titulo=Gerenciador de processos&parametro=&prf=01&mnusel=2805&&");
			System.out.println(driver.getTitle());
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@When("^Iniciamos o processo do Gerenciador$")
	public void Processo() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		try {
			// Clina no botão "Iniciar novo Processo"
			driver.findElement(By.id("btnExecutar")).click();

			// Seleciona o processo " Integração Geral - Operações"
			driver.findElement(
					By.xpath("//tbody//*[@id='divStartProcessTree']//*[text()='Integração Geral – Operações']"))
					.click();

			// Clica no botão Concluir
			driver.findElement(By.id("btnConcluir")).click();
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
		// //Espera o campo Situação esta como "PENDENTE"
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("msgRunning"),
				"Não existem processos em execução."));
		Assert.assertEquals("Não existem processos em execução.", By.id("msgRunning"));

		// Printa tela
		BufferedImage GerenProcesso = robot
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(GerenProcesso, "JPEG", new File("ScreenShot/Gerenciado_de_Processo/GerenProcesso.jpg"));
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Then("^Clicamos no log e printamos$")
	public void log() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy \nHH:mm");

		System.out.println(sdf.format(new Date()));

		JOptionPane.showMessageDialog(null, "Teste Concluido!");
		 driver.quit();
	}
}
