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
import org.testng.Assert;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class ProcIntOperacao {
	private WebDriver processo;

	@Before("@Processo")
	public void setUp() {
		// Abre o navegador com o Driver Explorer
		System.setProperty("webdriver.ie.driver", "C:/SeleniumDriver/IEDriverServer.exe");
		processo = new InternetExplorerDriver();
		processo.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		processo.manage().window().maximize();

		// Abre a pagina da Mapfre
		// driver.get("http://172.20.152.47/mapfrecap//Coreon.Acesso/frmLogin.aspx");/DEVR2
		// Abre a pagina da Mapfre
		processo.get("http://10.206.28.128/mapfrecap/"); // SUS

	}

	@Given("^que efetuei o cadastro de proposta e devera rodar o processo para o sistma atualizar$")
	public void Caminho() throws InterruptedException, AWTException, IOException {
		Robot robot = new Robot();
		Thread.sleep(1000);
		// Direciona para pagina "Gerenciador de Processos"
		processo.get(
				"http://10.206.28.128/mapfrecap//processes/frmProcess.aspx?endereco=../../../processes/frmProcess.aspx&usu=0001&titulo=Gerenciador de processos&parametro=&prf=01&mnusel=2805&&");

		Thread.sleep(500);
		// Clina no botão "Iniciar novo Processo"
		processo.findElement(By.id("btnExecutar")).click();

		Thread.sleep(1000);
		// Seleciona o processo " Integração Geral - Operações"
		processo.findElement(
				By.xpath("//tbody//*[@id='divStartProcessTree']//*[text()='Integração Geral – Operações']")).click();
		
		//Clica no botão Concluir
		processo.findElement(By.id("btnConcluir")).click();

		// Espera finalizar processo

//		WebDriverWait wait = new WebDriverWait(processo, 20);// corrigir
//		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("// fieldset//*[@id='msgRunning']"),
//				"Não existem processos em execução."));

		// Printa tela
		BufferedImage GerenProcesso = robot
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(GerenProcesso, "JPEG", new File("ScreenShot/Gerenciado_de_Processo/GerenProcesso.jpg"));

		Thread.sleep(1000);
		JOptionPane.showMessageDialog(null, "Teste Concluido!");
		processo.quit();
	}
}
