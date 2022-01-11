package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class CompraPage {
    protected WebDriver driver;

    private By BotaoImagemItem = By.className("produto-sobrepor");
    private By BotaoComprar = By.className("comprar");
    private By BotaoFinalizaCarrinho = By.xpath("//button[text()='Finalizar compra']");
    private By BotaoFinalizaPagamento = By.id("finalizarCompra");
    private By BotaoUsarCupom = By.xpath("//button[text()='Usar cupom']");
    private By botaoSubMenuProduto = By.className("categoria-id-15610608");
    private By radioBoleto = By.id("radio-mercadopagov1-520160");
    private By radioMercadoPago = By.id("radio-mercadopagov1-18");
    private By radioCartoCredito = By.id("radio-cartao");
    private By radioFrete = By.name("formaEnvio");
    private By campoCupom = By.id("usarCupom");
    private By tagCupom = By.cssSelector("span[class='cupom-codigo borda-alpha']");
    private By nomeCupom = By.xpath("//*[@id=\"corpo\"]/div/div[1]/div/div[3]/div/div/div[2]/div/table/tbody/tr[4]/td/div/strong");
    private By numeroPedido = By.className("numero-pedido");
    private By tipoSituacao = By.cssSelector("b[class='cor-principal'");
    private By descricaoDesconto = By.cssSelector("div[class='cupom-valor']");
    private By valorDesconto = By.xpath("//*[@id='corpo']/div/div[1]/div/div[3]/div/div/div[2]/div/table/tbody/tr[5]/td/div/strong");
    private By msgErroCupom = By.cssSelector("div[data-type='danger']");
    private By infoDescontoProduto = By.className("bandeira-promocao");
    private By valorCheioProduto = By.className("preco-venda");
    private By valorDescontoProduto = By.className("preco-promocional");
    private By valorFinal = By.className("valor-total");
    private By quanidadeMais = By.className("icon-plus");


    public CompraPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (!driver.getTitle().equals("QA Store Desafio")) {
            throw new IllegalStateException("Está não é a pagina QA Store Desafio. A pagina corrente é " + driver.getCurrentUrl());
        }

    }

    public void selecionaBotaoImagem(String nomeProduto, String valor, String infoDesconto, String valorDesconto) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(BotaoImagemItem));
        List<WebElement>  listaProduto = driver.findElements(BotaoImagemItem);

        int conta = 0;
        for (WebElement obterProduto : listaProduto ) {
            String nome = obterProduto.getAttribute("title");
            if (infoDesconto != null && valorDesconto != null) {
                List<WebElement>  listaPrecoTotal = driver.findElements(valorCheioProduto);
                List<WebElement> listaPrecoDesconto = driver.findElements(valorDescontoProduto);
                List<WebElement> listaNumDesconto = driver.findElements(infoDescontoProduto);
                String precoCheio = listaPrecoTotal.get(conta).getText();
                String precoDesconto = listaPrecoDesconto.get(conta).getText();
                String numeroDesconto = listaNumDesconto.get(conta).getText();
                if (nome.equals(nomeProduto)) {
                    assertEquals(precoCheio, valor);
                    assertEquals(precoDesconto, valorDesconto);
                    assertTrue(numeroDesconto, true);
                    assertEquals(numeroDesconto, infoDesconto);
                    obterProduto.click();
                    break;
                }
            } else {
                List<WebElement>  listaPrecoNormal = driver.findElements(valorDescontoProduto);
                String preco = listaPrecoNormal.get(conta).getText();
                if (nome.equals(nomeProduto)) {
                    assertEquals(preco, valor);
                    obterProduto.click();
                    break;
                }
            }
            conta++;
        }

    }

    public void selecionaFormaPagamento(String nomeFormaPgto) {
        WebDriverWait esperaElemento = new WebDriverWait(driver, 60);
        if (nomeFormaPgto.equals("boleto")) {
            esperaElemento.until(ExpectedConditions.visibilityOfElementLocated(radioBoleto));
            driver.findElement(radioBoleto).click();
        } else if (nomeFormaPgto.equals("mercado pago")) {
            esperaElemento.until(ExpectedConditions.visibilityOfElementLocated(radioMercadoPago));
            driver.findElement(radioMercadoPago).click();
        } else if (nomeFormaPgto.equals("cartao de credito")){
            esperaElemento.until(ExpectedConditions.visibilityOfElementLocated(radioCartoCredito));
            driver.findElement(radioCartoCredito).click();
        } else {
            System.out.println("RadioButtom não encontrado");
        }

    }

    public void clicaBotao(String nomeBotao) {
        WebDriverWait esperaElemento = new WebDriverWait(driver, 60);
        if (nomeBotao.equals("Comprar")) {
            esperaElemento.until(ExpectedConditions.visibilityOfElementLocated(BotaoComprar));
            driver.findElement(BotaoComprar).click();
        } else if (nomeBotao.equals("Usar cupom")) {
            esperaElemento.until(ExpectedConditions.visibilityOfElementLocated(BotaoUsarCupom));
            driver.findElement(BotaoUsarCupom).click();
        } else if (nomeBotao.equals("Finalizar compra")) {
            esperaElemento.until(ExpectedConditions.visibilityOfElementLocated(BotaoFinalizaCarrinho));
            driver.findElement(BotaoFinalizaCarrinho).click();
        } else  {
            System.out.println("Botão não encontrado");
        }

    }

    public void selecionaFreteEntrega(String nomeTipoEntrega) {
        List<WebElement> listaRadio = driver.findElements(radioFrete);

        for (WebElement selecionaRadio : listaRadio) {
            String valorRadio = selecionaRadio.getAttribute("data-code");
            if (valorRadio.equals(nomeTipoEntrega)) {
                selecionaRadio.click();
                break;
            }
        }

    }

    public void selecionaOpcaoMenuBotaoProduto() {
        driver.findElement(botaoSubMenuProduto).click();

    }

    public void clicaBotaoFinalizaCompra(String nomeBotao) {
        new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(BotaoFinalizaPagamento));
        driver.findElement(BotaoFinalizaPagamento).click();
    }

    public void adicionaQuantidadeProduto(int quantidade) {
        for (int i = 1; i < quantidade; i++) {
            driver.findElement(quanidadeMais).click();
        }

    }

    public void digitaCampoCupom(String nomeCupom) {
        driver.findElement(campoCupom).sendKeys(Keys.chord(nomeCupom));

    }

    public void validaTagCupom(String nomeTag) {
        String textoTagCupom = driver.findElement(tagCupom).getText();
        assertEquals(nomeTag, textoTagCupom);

    }

    public void validarCampoCupom(String nomeCupom) {
        String textoNomeCupom = driver.findElement(campoCupom).getText();
        assertNotEquals(nomeCupom, textoNomeCupom);
    }

    public void validaDescricaoDescontoCupom(String descricao) {
        String textoDescricao = driver.findElement(descricaoDesconto).getText().replaceAll("\\n", "");
        assertEquals(descricao, textoDescricao);
    }

    public void validaMsgErroCupom(String msg) {
        String textoMsg = driver.findElement(msgErroCupom)
                .getText().replaceAll("\\n", "")
                .replace("×", "");;
        assertEquals(msg, textoMsg);

    }

    public void validaNumeroPedido() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(numeroPedido));
         driver.findElement(numeroPedido).isDisplayed();

    }

    public void validaNomeCupom (String nome) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(nomeCupom));
        String textoCupom = driver.findElement(nomeCupom).getText();
        assertEquals(nome, textoCupom);
    }

    public void validaSituacaoPedido(String situacao) {
        String apresentaSituacao = driver.findElement(tipoSituacao).getText();
        assertEquals(situacao, apresentaSituacao);

    }

    public void validaDescontoAplicado (String desconto) {
        String obterDesconto = driver.findElement(valorDesconto).getText();
        assertEquals(desconto, obterDesconto);
    }

    public void validaCupomNaoAparece() {
        int teste = driver.findElements(nomeCupom).size();
        assertFalse(teste == 0);

    }

    public void validaValorFinal(String valor) {
        String obterValor = driver.findElement(valorFinal).getText();
        assertEquals(valor, obterValor);

    }

}
