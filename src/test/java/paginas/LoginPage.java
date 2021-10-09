package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver navegador;

    public LoginPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public LoginPage informarUsuario(String usuario) {
        navegador.findElement(By.id("usuario")).sendKeys(usuario);
        return this;
    }

    public LoginPage informarSenha(String senha) {
        navegador.findElement(By.id("senha")).sendKeys(senha);
        return this;
    }

    public ListaProdutosPage submeterFormularioLogin() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaProdutosPage(navegador);
    }



}
