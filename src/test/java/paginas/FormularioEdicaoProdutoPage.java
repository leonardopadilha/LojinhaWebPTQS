package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioEdicaoProdutoPage {
    private WebDriver navegador;

    public FormularioEdicaoProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public String capturarMensagemDeSucessoRetornada() {
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }

    public ListaProdutosPage retornarListagemProdutos() {
        navegador.findElement(By.partialLinkText("LISTA DE PRODUTOS")).click();
        return new ListaProdutosPage(navegador);
    }
}
