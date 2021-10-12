package modulos.produtos;

import modulos.interfaces.Mensagem;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Web do Módulo de Produtos")
public class ProdutosTest {
    private WebDriver navegador;
    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\drivers\\chromedriver.exe");
        this.navegador = new ChromeDriver();
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
        this.loginPage = new LoginPage(navegador);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        String msgApresentada = loginPage
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioLogin()
                .acessarAdicionarProduto()
                .informarNomeProduto("Vídeo Game")
                .informarValorProduto("000")
                .informarCoresProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();
        assertEquals(Mensagem.VALOR_NAO_PERMITIDO, msgApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor maior do que 7.000,00")
    public void testNaoEPermitidoRegistrarProdutoComValorMaiorQueSeteMil() {
        String msgApresentada = loginPage
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioLogin()
                .acessarAdicionarProduto()
                .informarNomeProduto("Caixa de Som")
                .informarValorProduto("700001")
                .informarCoresProduto("branco, azul")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();
        assertEquals(Mensagem.VALOR_NAO_PERMITIDO, msgApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos com valor de 0,01")
    public void testPossoAdicionarProdutosComValorDeUmCentavo() {
        String msgProdutoCadastradoComSucesso = loginPage
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioLogin()
                .acessarAdicionarProduto()
                .informarNomeProduto("Monitor")
                .informarValorProduto("001")
                .informarCoresProduto("preto, branco")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemDeSucessoRetornada();
        assertEquals(Mensagem.PRODUTO_CADASTRADO_COM_SUCESSO, msgProdutoCadastradoComSucesso);
    }

    @Test
    @DisplayName("Posso criar um produto com valor de 7.000,00")
    public void testPossoAdicionarProdutoComValorDeSeteMilReais() {
        String msgProdutoCadastradoComSucesso = loginPage
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioLogin()
                .acessarAdicionarProduto()
                .informarNomeProduto("IPhone 13")
                .informarValorProduto("700000")
                .informarCoresProduto("preto, branco, rosa, azul, verde")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemDeSucessoRetornada();
        assertEquals(Mensagem.PRODUTO_CADASTRADO_COM_SUCESSO, msgProdutoCadastradoComSucesso);
    }

    @Test
    @DisplayName("Excluir item")
    public void testExcluirItemCadastrado() {
        String msgExclucao = loginPage
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioLogin()
                .acessarAdicionarProduto()
                .informarNomeProduto("Teclado Multilaser")
                .informarValorProduto("50000")
                .informarCoresProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .retornarListagemProdutos()
                .excluirItemSalvo()
                .capturarMensagemDeExclusaoComSucesso();
        assertEquals(Mensagem.PRODUTO_EXCLUIDO_COM_SUCESSO, msgExclucao);
    }

    @Test
    @DisplayName("Editar um item para incluir componente")
    public void testEditarItemParaIncluirComponente() {
        String msgRetornoComponenteCadastradoComSucesso = loginPage
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioLogin()
                .acessarAdicionarProduto()
                .informarNomeProduto("Mouse")
                .informarValorProduto("12500")
                .informarCoresProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .adicionarComponenteProduto()
                .informarNomeComponente()
                .informarQuantidadeComponente()
                .salvarInformacoesComponente()
                .capturarMensagemDeSucessoRetornada();
        assertEquals(Mensagem.PRODUTO_CADASTRADO_COM_SUCESSO, msgRetornoComponenteCadastradoComSucesso);
    }

    @Test
    @DisplayName("Excluir componente")
    public void testExcluirComponente() throws InterruptedException {
        loginPage
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioLogin()
                .acessarAdicionarProduto()
                .informarNomeProduto("Mouse")
                .informarValorProduto("12500")
                .informarCoresProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .adicionarComponenteProduto()
                .informarNomeComponente()
                .informarQuantidadeComponente()
                .salvarInformacoesComponente()
                .excluirComponenteIncluso();
    }
    @AfterEach
    public void afterEach() {
       navegador.quit();
    }

}
