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
		String result = "//*[@id='main']//*[@class='findSection']//*[@class='findSectionHeader']//*[@name='tt']//..//..//*[@class='findList']//*[@class='result_text']//a[text()='It']";
		String [] actorNames = {"Jaeden Lieberher","Jeremy Ray Taylor","Sophia Lillis","Finn Wolfhard","Chosen Jacobs","Jack Dylan Grazer","Wyatt Oleff",
				                "Bill Skarsgård","Nicholas Hamilton","Jake Sim","Logan Thompson","Owen Teague","Jackson Robert Scott","Stephen Bogaert",
				                "Stuart Hughes"};
		String [] characterNames = {"Bill Denbrough","Ben Hanscom","Beverly Marsh","Richie Tozier","Mike Hanlon","Eddie Kaspbrak","Stanley Uris","Pennywise",
								"Henry Bowers","Belch Huggins","Victor Criss","Patrick Hockstetter","Georgie Denbrough","Mr. Marsh","Officer Bowers"};
		//manda a llamar funcion que busca pelicula
		searchMovie(movie);
		findMovie(result);
		for(int i = 0; i < actorNames.length; i++) {
			try{
				String xpathColumnaActor = "//table[@class='cast_list']//td[@class='itemprop']";
				WebElement actor = 
					driver.findElement(By.xpath(xpathColumnaActor+"//span[text() = '" + actorNames[i] +"']"));
				WebElement character = 
					driver.findElement(By.xpath(xpathColumnaActor+"/following-sibling::td[@class='character']//a[text()='"+ characterNames[i]+ "']"));
			    //valida que ambos existan
				validateExisting(actor,character);
			}catch(org.openqa.selenium.NoSuchElementException e) {
				System.out.println("Actor y/o personaje no existen!");
			}
		}		
	}

	private static void tearDown() {
		System.out.println("Fin del Script!");
	}
	
	private static void searchMovie(String movie) {
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
	}

	private static void findMovie(String result) {
		//Espera para que cargue la pagina de resultados
		WebDriverWait waitResultsLoad = new WebDriverWait(driver,30);
		waitResultsLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		//Busca elemento deseado dentro de los resultados
		WebElement desiredResult = driver.findElement(By.xpath(result));
		desiredResult.click();
		
	}
	
	private static void validateExisting(WebElement actor, WebElement character) {
		System.out.println("Actor: "+actor.getText()+"\t\tPersonaje: "+character.getText());
		
	}
	
}
