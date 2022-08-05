package modulos.produtos;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
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
       String mensagemApresentada= new LoginPage(navegador)
                .informarOUsuario(dotenv.get("env_usuario"))
                .informarASenha(dotenv.get("env_senha"))
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Bolacha")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto,branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na faixa de 0,01 a 7.000,00")
    public void testPossoAdicionarProdutosComValorDeUmCentavoASeteMilReais(){

        //logar na lojinha
        String mensagemApresentada= new LoginPage(navegador)
                .informarOUsuario(dotenv.get("env_usuario"))
                .informarASenha(dotenv.get("env_senha"))
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Bolacha")
                .informarValorDoProduto("5000")
                .informarCoresDoProduto("preto,branco")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso",mensagemApresentada);

    }

    @AfterEach
    public void afterEach(){
        //vou fechar navegador
        navegador.quit();

    }

}
