package MapfreCap;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class PagamentoSteps {

	private WebDriver driver;

	@Before("@Pagamento")
	public void setUp() throws InterruptedException {

		System.setProperty("webdriver.ie.driver", "C:/SeleniumDriver/IEDriverServer.exe");

		// Drive Internet Explorer
		driver = new InternetExplorerDriver();
		// Maximiza a tela
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Abre o ambiente MapfreCap
		driver.get("http://10.206.28.128/mapfrecap/");

		Thread.sleep(1000);
		// Entra no ambiete MapfreCap
		driver.get("http://10.206.28.128/mapfrecap/tkgs_acesso/asp/framelogin.asp?usu=0025&prf=04");

		Thread.sleep(1000);
		// Entra na tela "BAIXA DE PAGAMENTO"
		driver.get(
				"http://10.206.28.128/mapfrecap/tkgs_cobranca.NET/cob_lotes_cobranca_aviso.aspx?usu=0001&prf=01&mnusel=240205");
		System.out.println(driver.getTitle());

	}

	@Given("^que o gerenciador de processo foi rodado e estamos na pagina da baixa de Pagamento$")
	public void IncluirBanco() throws InterruptedException {

		// Clica na lupa de pesquisa
		driver.findElement(By.id("btnAgenteCobrador")).click();
		// Clica no botão "NOVO"
		driver.findElement(By.id("btnNovo")).click();
		// Clica na "LUPA"
		driver.findElement(By.id("btnAgenteCobrador")).click();

		Thread.sleep(1000);
		JOptionPane.showMessageDialog(null, "Teste Concluido!");
		driver.quit();

		// Altera o foco para tela de "CONSULTA"
		/*
		 * driver.switchTo().frame("//body/div/div");
		 * 
		 * // Clica no campo "ITEM" driver.findElement(By.id("cboItem")).click();
		 * 
		 * // Dimension total_frames =
		 * driver.findElement(By.xpath("//body/div/div")).getSize();
		 * 
		 * Thread.sleep(500);
		 * driver.findElement(By.xpath("//tbody//[@='cboItem']//*[text()='Código']")).
		 * click();
		 * 
		 * driver.findElement(By.id("cboCriterio")).click();
		 * 
		 * driver.findElement(By.id("txtSelecao")).click();
		 * 
		 * driver.findElement(By.id("btnPesquisar")).click();
		 * 
		 * // driver.switchTo.frame("Frame_ID");
		 */

	}

}
//NoSuchElementException