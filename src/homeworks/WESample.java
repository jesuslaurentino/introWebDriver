package homeworks;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;

public class WESample {

	public static void main(String[] args) {
		String path="C:\\test_automation\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",path);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=es"); 
		WebDriver driver = new ChromeDriver(options);
		
		driver.get("https://twitter.com/?lang=es");
		
		WebElement sessionS = driver.findElement(By.linkText("Iniciar sesión"));
		sessionS.click();
		
		String emailId = "session[username_or_email]";
		WebElement emailF = driver.findElement(By.name(emailId));
		emailF.sendKeys("jesuslaurentino@hotmail.com");
		
		String passId = "session[password]";
		WebElement passF = driver.findElement(By.name(passId));	
		passF.sendKeys("venadomazatleco");
		
		String loginId = "//input[@value='Iniciar sesión']";
		WebElement loginLink = driver.findElement(By.xpath(loginId));
		loginLink.click();
		
		int flag=0;
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		do {
			jse.executeScript("window.scrollBy(0,250)", "");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			try{ 
				String xpathSearch = "//*[contains(text(),'Lopez Obrador')]";
				flag = Integer.parseInt(driver.findElement(By.xpath(xpathSearch)).getText());			}
			catch ( org.openqa.selenium.NoSuchElementException e) {
				
			}
			
		}while(flag==0);
		
		System.out.println("End Of TestScript Run");
	}

}
