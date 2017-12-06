package MapfreCap;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class Pagamento {
	private WebDriver driver;
	String nome = "MARIA DA GLORIA";
	String valor = "10000";
	String titulo = "2";
	String ambiente = "DEVR2";

	@Before("@Pagamento")
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
	@Given("^que o gerenciador de processo foi rodado e estamos na pagina da baixa de Pagamento$")
	public void pagamento() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('txtLogin_I').setAttribute('value', 'adm')");
		js.executeScript("document.getElementById('txtSenha_I').setAttribute('value', 'mcap007')");
		
		driver.findElement(By.name("btnEntrar")).click();
		
	}

}
