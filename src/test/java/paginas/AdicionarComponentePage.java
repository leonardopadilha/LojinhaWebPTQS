package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdicionarComponentePage {
    private WebDriver navegador;

    public AdicionarComponentePage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public AdicionarComponentePage informarNomeComponente() {
        navegador.findElement(By.id("componentenomeadicionar")).sendKeys("Pilha");
        return this;
    }

    public AdicionarComponentePage informarQuantidadeComponente() {
        navegador.findElement(By.id("componentequantidadeadicionar")).sendKeys("2");
        return this;
    }

    public FormularioEdicaoProdutoPage salvarInformacoesComponente() {
        navegador.findElement(By.partialLinkText("SALVAR COMPONENTE")).click();
        return new FormularioEdicaoProdutoPage(navegador);
    }
}
