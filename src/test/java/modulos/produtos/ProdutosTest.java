package modulos.produtos;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Teste Automação Web Módulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;
    Dotenv dotenv = Dotenv.load();





    @BeforeEach
    public void beforeEach(){
        //abrir o navegador
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver103\\chromedriver.exe");
        this.navegador = new ChromeDriver();
        navegador.manage().window().maximize(); //maximizar a janela

        //abrir pagina lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

        //definir tempo de espera 5s antes de falhar o teste
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero(){

        //logar na lojinha
        new LoginPage(navegador)
                .informarOUsuario(dotenv.get("usuario"))
                .informarASenha(dotenv.get("senha"))
                .submeterFormularioDeLogin();

        //abrir tela de produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        //preencher dados do produto e o valor sera igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys("Bolacha");
        navegador.findElement(By.id("produtovalor")).sendKeys("650");
        navegador.findElement(By.id("produtocores")).sendKeys("Marron, Amarelo");

        //salvar o produto cadastrado
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        //validar a msg de erro foi apresentada
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemToast);


    }

    @AfterEach
    public void afterEach(){
        //vou fechar navegador
        navegador.quit();

    }

}
