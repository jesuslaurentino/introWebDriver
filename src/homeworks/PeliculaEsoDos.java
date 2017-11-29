package homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PeliculaEsoDos {
	
	static WebDriver driver;

	public static void main(String[] args) {
		setUp();
		testEso();
		tearDown();
		
		
	}

	private static void setUp() {
		String path="C:\\test_automation\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en");
		driver = new ChromeDriver(options);
		driver.get("http://www.imdb.com/");
		
	}

	private static void testEso() {
		String movie = "IT";
		String [] actorNames = {"actor1","actor2","actor3","actor4","actor5"};
		String [] characterNames = {"character1","character2","character3","character4","character5"};
		//manda a llamar funcion que busca pelicula
		findMovie(movie);
		
		for(int i = 0; i < actorNames.length; i++) {
			String xpathColumnaActor = 
					"//table[@class='cast_list']//td[@class='itemprop']";
			WebElement actor = 
					driver.findElement(By.xpath(xpathColumnaActor+"//span[text() = '" + actorNames[i] +"']"));
			WebElement character = 
					driver.findElement(By.xpath(xpathColumnaActor+"/following-sibling::td[@class='character']//a[text()='"+ characterNames[i]+ "']"));
			//validar que ambos existan
		}		
	}

	private static void tearDown() {
		
	}
	
	private static void findMovie(String movie) {
		//Espera para que cargue la pagina
		WebDriverWait waitHomepageLoad = new WebDriverWait(driver,30);
		waitHomepageLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		//Busca la barra de busqueda y escribe el nombre de la pelicula en ella.
		String searchBarId = "navbar-query";
		WebElement searchBarField = driver.findElement(By.id(searchBarId));
		searchBarField.clear();
		searchBarField.click();
		searchBarField.sendKeys(movie);
		//Busca el botón de busqueda y le da click.
		String searchButtonId = "navbar-submit-button";
		WebElement searchButton = driver.findElement(By.id(searchButtonId));
		searchButton.click();
		WebDriverWait waitResultsLoad = new WebDriverWait(driver,30);
		waitResultsLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		
	}

}
