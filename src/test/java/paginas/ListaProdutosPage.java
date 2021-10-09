package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaProdutosPage {
    private WebDriver navegador;

    public ListaProdutosPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public FormularioAdicaoProdutoPage acessarAdicionarProduto() {
        navegador.findElement(By.partialLinkText("ADICIONAR PRODUTO")).click();
        return new FormularioAdicaoProdutoPage(navegador);
    }

    public String capturarMensagemApresentada() {
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
