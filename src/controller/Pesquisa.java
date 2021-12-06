package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import java.net.URL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class Pesquisa {

	static WebDriver driver;

	public Pesquisa(WebDriver driver) {
		Pesquisa.driver = driver;
	}

	public boolean abrirPagina(String url) {

		boolean continuar = false;

		try {
			driver.get(url);
			Thread.sleep(2000);
			continuar = true;

		} catch (Exception e) {
			System.out.println("erro, sem conexão com o opentdb!");

			continuar = false;

			String winHandleBefore = driver.getWindowHandle();

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}

			driver.close();

			driver.switchTo().window(winHandleBefore);
			driver.close();

		}

		if (continuar) {

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;

		} else {
			// sem conexao com a internet, cancelar
			driver.close();
			return false;
		}

	}

	public void fazerPesquisa(String palavaraChave) {

		WebElement entQuery = driver.findElement(By.name("query"));
		entQuery.sendKeys(palavaraChave);

		WebElement botaoPesquisar = driver.findElement(By.xpath("//*[@role='button']"));
		botaoPesquisar.click();

	}

	public void fazerPesquisaPorCategoria(String palavaraChave) {

		WebElement entQuery = driver.findElement(By.name("query"));
		entQuery.clear();
		entQuery.sendKeys(palavaraChave);

		WebElement comboboxElement = driver.findElement(By.id("type"));
		Select combobox = new Select(comboboxElement);
		combobox.selectByVisibleText("Category");

		WebElement botaoPesquisar = driver.findElement(By.xpath("//*[@role='button']"));
		botaoPesquisar.click();

	}

	public String getTexto() {
		WebElement mensagem = driver.findElement(By.xpath("//*[@class='alert alert-danger']"));
		return mensagem.getText();
	}
	
	
	public int numItens() {
      	List<WebElement> botoes = driver.findElements(By.tagName("tr"));
      	return botoes.size() - 1;
	}
	
	public boolean isPaginacao() {
		try {
		WebElement mensagem = driver.findElement(By.xpath("//*[@class='pagination pagination-lg']"));

		if(mensagem != null)
			return true;
		else 
			return false;
		}catch(NoSuchElementException e) {
			
			return false;
		}
	}

}
