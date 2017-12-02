package homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import libs.Input;
import java.time.Year;
import java.util.List;

public class verificarElenco {

	static WebDriver driver;
	static String movie;
	static int year;

	public static void main(String[] args) {
		setUp();
		testMovie();
		tearDown();
	}

	private static void setUp() {
		System.out.print("Nombre de la pelicula a buscar: ");
		movie = getMovie();
		System.out.print("Año de la pelicula: ");
		year = getYear();
		String path="C:\\test_automation\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en");
		driver = new ChromeDriver(options);
		driver.get("http://www.imdb.com/");
	}

	private static void testMovie() {
		searchMovie(movie);
		findMovie(year);
	}
	
	private static void tearDown() {
		//driver.close();
		System.out.println("Fin del Script!");
	}
	
	private static String getMovie() { //obtiene el input del usuario para nombre de la pelicula
		String inputMovie = Input.get_line();
		return inputMovie;
	}

	private static int getYear() { //obtiene el input del usuario para el año de la pelicula
		int inputYear = Input.get_int();
		if (inputYear>=1874 && inputYear<=Year.now().getValue())
		    return inputYear;
		else {
			System.out.println("Año incorrecto!");
			return 0;
			}
	}
	
	private static void searchMovie(String movieSearch) {
		//Espera para que cargue la pagina principal
		WebDriverWait waitHomepageLoad = new WebDriverWait(driver,15);
		waitHomepageLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		//Busca la barra de busqueda y escribe el nombre de la pelicula en ella.
		String searchBarId = "navbar-query";
		WebElement searchBarField = driver.findElement(By.id(searchBarId));
		searchBarField.clear();
		searchBarField.click();
		searchBarField.sendKeys(movieSearch);
		//Busca el botón de busqueda y le da click.
		String searchButtonId = "navbar-submit-button";
		WebElement searchButton = driver.findElement(By.id(searchButtonId));
		searchButton.click();
	}
	
	private static void findMovie(int year) {
		//Declara el xpath de los resultados en el cual buscar el titulo de la pelicula
		String resultBeginnig = "//*[@id='main']//*[@class='findSection']//*[@class='findSectionHeader']//*[@name='tt']//..//..//*[@class='findList']//*[@class='result_text']//a";
		String aux="";
		//String resultEnding = "']";
		//Espera para que cargue la pagina de resultados
		WebDriverWait waitResultsLoad = new WebDriverWait(driver,15);
		waitResultsLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		//Obtiene el conteo de resultados
		int count =  driver.findElements(By.xpath(resultBeginnig)).size();
		//Por cada resultado hace click en el y valida si es la pelicula que se busca con el actor referencia.
	    for (int i=0; i<count ; i++) {
	        //driver.findElements(By.tagName("a")).get(i).click();
	    	aux = driver.findElements(By.xpath(resultBeginnig)).get(i).getText();
	    	System.out.println("Link"+i+": "+aux);
	    	driver.findElements(By.xpath(resultBeginnig)).get(i).click();
	    	//agregar wait
	    	//Si es el año correcto llama a desplegar elenco
	    	if(validateMovie(year))
	    	{
	    		validateCast();
	    		break;
	    	}else {
	    		driver.navigate().back();
	    	aux = "";}
	    }
	    
		
	}
	
	private static boolean validateMovie(int year) {//valida si la pelicula es del año ingresado
		//Espera para que cargue la pagina de la pelicula
		String yearS = "null";
		WebDriverWait waitResultNLoad = new WebDriverWait(driver,15);
		waitResultNLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		String resultYearSearch = "//*[@id='titleYear']//a[text()='"+year+"']";
		try {
			yearS = driver.findElement(By.xpath(resultYearSearch)).getText();
			System.out.println("Año "+yearS);		 
			}catch(org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No es la pelicula a buscar! ");
			return false;
			}
		return true;
		
	}
	
	private static void validateCast() {
		String xpathColumnaActor = "//table[@class='cast_list']//td[@class='itemprop']//span[@class='itemprop' and @itemprop='name']";
		String xpathColumnaRole = "//table[@class='cast_list']//td[@class='itemprop']//following-sibling::td[@class='character']";
		List <WebElement> xpathActor = driver.findElements(By.xpath(xpathColumnaActor));
		List <WebElement> xpathActorRole = driver.findElements(By.xpath(xpathColumnaRole));
		for(int i=0;i<xpathActor.size();i++) {	
				displayExisting(xpathActor.get(i),xpathActorRole.get(i));
		}		
		
	}

	private static void displayExisting(WebElement actor, WebElement character) {
		System.out.println("Actor: "+actor.getText()+"\t\tPersonaje: "+character.getText());
		
	}
	
}
