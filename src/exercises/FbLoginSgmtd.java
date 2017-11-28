package exercises;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FbLoginSgmtd {

	static By searchBoxLocator = By.name("q");
	static WebDriver driver;
	
	public static void main(String[] args) {
	//setup the environment
	setUp("http://www.facebook.com");
	//log in to facebook
	logIntoFacebook("geral.overhage.selenium@gmail.com", "T3st1234");
	//verify searchbox element exist
	verifyElementExists(searchBoxLocator);
	//tear down the configuration
	tearDown();
	}
	private static void tearDown() {
		driver.close();
		
	}
	private static void verifyElementExists(By locator) {
		
		WebElement elementX = driver.findElement(locator);
		Asserts.check(elementX.isDisplayed(), "Element not found.");
		
		
	}
	private static void logIntoFacebook(String user, String password) {
        String emailId = "email"; 
        String passId = "pass";
        String loginId = "u_0_2";
		WebElement email = driver.findElement(By.id(emailId));
		WebElement pass = driver.findElement(By.id(passId));
		//Agregar wait element is displayed
		WebElement loginB = driver.findElement(By.id(loginId));
		
		 //Introducir texto
        email.clear();
        email.sendKeys(user);
        pass.clear();
        pass.sendKeys(password);
        
        //Click entrar
        loginB.click();
		
	}
	private static void setUp(String url) {
		 String pathToDriver = "C:\\test_automation\\drivers\\chromedriver.exe";
		 System.setProperty("webdriver.chrome.driver", pathToDriver);
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("--lang=es"); 
		 
		 driver = new ChromeDriver(options);
		 driver.get(url);
	}


}
