package MapfreCap;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MapfreCapSteps {

	private WebDriver driver;

	@Before("@CadastroSimples")
	public void setUp() {
		System.setProperty("webdriver.ie.driver", "C:/SeleniumDriver/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.get("http://172.20.152.47/mapfrecap//Coreon.Acesso/frmLogin.aspx");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Given("^Que estamos com o sistema da MapfreCap aberto$")
	public void Abrir() throws InterruptedException {
		

	}

	@And("^efetuamos o login$")
	public void Logar() throws InterruptedException {
		driver.findElement(By.id("txtLogin_I")).click();
		driver.findElement(By.id("txtLogin_I")).sendKeys("adm");

		Thread.sleep(2000);
		driver.findElement(By.id("txtSenha_I")).click();
		driver.findElement(By.id("txtSenha_I")).sendKeys("mcap007");

		Thread.sleep(3000);
		driver.findElement(By.id("btnEntrar")).click();

	}

	@When("^seguimos o fluxo para iniciar o cadasstro da nova proposta$")
	public void CaminhoCadastro() throws InterruptedException {

	}

	@Then("^cadastramos a proposta$")
	public void Cadastro() throws InterruptedException {

	}
}
