package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    protected WebDriver driver;

    private By linkMinhaConta = By.linkText("Minha Conta");
    private By campoEmail = By.id("id_email");
    private By campoSenha = By.id("id_senha");
    private By botaoProsseguir = By.xpath("//button[text()='Prosseguir']");
    private By pessoaLogada = By.xpath("//*[@id=\"cabecalho\"]/div[2]/div[1]/div[2]/div[1]/div[1]/div/a");
    private By linkPaginaInicial = By.linkText("QA Store Desafio");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void selecionaMinhaConta() {
        driver.findElement(linkMinhaConta).click();

    }

    public void digitarEmail(String email) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(campoEmail));
        driver.findElement(campoEmail).sendKeys(Keys.chord(email));

    }

    public void digitaSenha(String senha) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(campoSenha));
        driver.findElement(campoSenha).sendKeys(Keys.chord(senha));

    }

    public void clicaBotaoProsseguir() {
        driver.findElement(botaoProsseguir).click();

    }

    public void validaCampoPessoaLogadaAparce() {
        boolean validaCampoAparece = driver.findElement(pessoaLogada).isEnabled();
        Assert.assertEquals(validaCampoAparece, true);

    }

    public void selecionaPaginaInicial() {
        driver.findElement(linkPaginaInicial).click();

    }

}
