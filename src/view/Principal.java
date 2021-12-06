package view;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import controller.Pesquisa;

public class Principal {

	public static void main(String[] args) {

		FirefoxOptions options = new FirefoxOptions();
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(options);

		Pesquisa pesquisa = new Pesquisa(driver);
		boolean paginaAberta = pesquisa.abrirPagina("https://opentdb.com/browse.php");

		if (paginaAberta) {
			pesquisa.fazerPesquisa("Science: Computers");

			String retorno = pesquisa.getTexto();

			System.out.println("Conte�do: " + retorno);
			
			
			pesquisa.fazerPesquisaPorCategoria("Science: Computers");
			int num_itens = pesquisa.numItens();
			System.out.println("N�mero de Itens: " + num_itens);

			
			boolean controle_paginacao = pesquisa.isPaginacao();
			if(controle_paginacao) 
				System.out.println("H� Controle de P�gina��o");
			else
				System.out.println("N�o h� Controle de P�gina��o");

			
		} else {
			System.out.println("Erro");

		}
	}

}
