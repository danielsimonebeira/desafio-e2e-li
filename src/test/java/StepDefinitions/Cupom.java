package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import Pages.CompraPage;
import Pages.LoginPage;
import DataProviders.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Cupom {
    String url, loginEmail, loginSenha;
    WebDriver driver = null;
    CompraPage compraPage;
    LoginPage loginPage;
    ConfigFileReader configFileReader;

    @Before
    public void iniciar() {
        configFileReader = new ConfigFileReader();
        url = configFileReader.getApplicationUrl("urle2e");
        loginEmail = configFileReader.getAplcationLogin("loginEmail");
        loginSenha = configFileReader.getAplcationLogin("loginSenha");
        System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
        ligarChrome();
        driver.get(url);

        compraPage = new CompraPage(driver);
        loginPage = new LoginPage(driver);

        loginPage.selecionaMinhaConta();
        loginPage.digitarEmail(loginEmail);
        loginPage.digitaSenha(loginSenha);
        loginPage.clicaBotaoProsseguir();
        loginPage.validaCampoPessoaLogadaAparce();
        loginPage.selecionaPaginaInicial();

    }

    @After
    public void finalizar() {
        if (driver != null) {
            driver.quit();
        }

    }

    @Given("seleciono o produto {string} com valor de {string}")
    public void seleciono_o_produto_com_valor_de(String nomeProduto, String valorProduto) {
        compraPage.selecionaBotaoImagem(nomeProduto, valorProduto, null, null);

    }

    @Given("seleciono o produto {string} de {string}, {string}% de desconto do valor de {string}")
    public void seleciono_o_produto_de_de_desconto_do_valor_de(String nomeProduto, String valorDesconto, String porcentagemDesconto, String valorCheio) {
        compraPage.selecionaOpcaoMenuBotaoProduto();
        compraPage.selecionaBotaoImagem(nomeProduto, valorCheio, porcentagemDesconto + "% DESCONTO", valorDesconto);

    }

    @When("clicar no botão {string}")
    public void clicar_no_botão(String botao) {
        compraPage.selecionaFreteEntrega("retirar_pessoalmente");
        compraPage.clicaBotao(botao);

    }

    @Then("o sistema deve apresentar a tag de {string} no carrinho")
    public void o_sistema_deve_apresentar_a_tag_de_no_carrinho(String tagCupom) {
        compraPage.validaTagCupom(tagCupom);

    }

    @Then("sistema não apresenta a tag referente ao cupom {string} no carrinho")
    public void sistema_não_apresenta_a_tag_referente_ao_cupom_no_carrinho(String nomeCupom) {
        compraPage.validarCampoCupom(nomeCupom);

    }

    @Then("sistema gera  número do pedido")
    public void sistema_gera_número_do_pedido() {
        compraPage.validaNumeroPedido();

    }


    @And("que aciono a opção {string}")
    public void que_aciono_a_opção(String botao) {
        compraPage.clicaBotao(botao);

    }

    @And("no campo cupom de desconto preencho com o cupom válido {string}")
    public void no_campo_cupom_de_desconto_preencho_com_o_cupom_válido(String nomeCupom) {
        compraPage.digitaCampoCupom(nomeCupom);

    }

    @And("aplica a informação {string} na coluna ao lado da tag de cupom")
    public void aplica_a_informação_na_coluna_ao_lado_da_tag_de_cupom(String descricaoDesconto) {
        compraPage.validaDescricaoDescontoCupom(descricaoDesconto);

    }

    @And("no campo Cupom de desconto for preencho com o cupom inválido {string}")
    public void no_campo_cupom_de_desconto_for_preencho_com_o_cupom_inválido(String nomeCupom) {
        compraPage.digitaCampoCupom(nomeCupom);

    }

    @And("não aplica o cupom apresentando mensagem de erro {string}.")
    public void não_aplica_o_cupom_apresentando_mensagem_de_erro(String msgErro) {
        compraPage.validaMsgErroCupom(msgErro);

    }

    @And("etapa de opção de pagamento selecionar {string}")
    public void etapa_de_opção_de_pagamento_selecionar(String opcaoPgto) {
        compraPage.selecionaFormaPagamento(opcaoPgto);

    }

    @And("clicar em {string}")
    public void clicar_em( String botao)  {
        compraPage.clicaBotaoFinalizaCompra(botao);

    }

    @And("apresenta situacao {string}")
    public void apresenta_situacao(String situacao) {
        compraPage.validaSituacaoPedido(situacao);

    }

    @And("o cupom utilizado {string}")
    public void o_cupom_utilizado(String nomeCupom) {
        compraPage.validaNomeCupom(nomeCupom);

    }

    @And("o desconto {string} aplicado")
    public void o_desconto_aplicado(String desconto) {
        compraPage.validaDescontoAplicado(desconto);

    }

    @And("não preencho o campo cupom de desconto com um cupom")
    public void não_preencho_o_campo_cupom_de_desconto_com_um_cupom() {
        compraPage.digitaCampoCupom("");

    }

    @And("não apresenta a tag cupom")
    public void não_apresenta_a_tag_cupom() {
        compraPage.validaCupomNaoAparece();

    }

    @And("o valor total de {string}")
    public void o_valor_total_de(String valorFinal) {
        compraPage.validaValorFinal(valorFinal);

    }

    @And("no campo cupom de desconto preencho com o cupom válido {string} que possui restrição de valor minimo do carrinho")
    public void no_campo_cupom_de_desconto_preencho_com_o_cupom_válido_que_possui_restrição_de_valor_minimo_do_carrinho(String nomeCupom) {
        compraPage.digitaCampoCupom(nomeCupom);

    }

    @And("no adicione a quantidade de {string} para o segundo produto")
    public void no_adicione_a_quantidade_de_para_o_segundo_produto(String quantidade) throws InterruptedException {
        compraPage.adicionaQuantidadeProduto(Integer.parseInt(quantidade));

    }

    public void ligarChrome() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

}
