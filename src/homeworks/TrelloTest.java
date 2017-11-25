package homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrelloTest {

	public static void main(String[] args) {
		//Set the path to chrome webdriver
		String path="C:\\test_automation\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",path);
		//set language options for the webdriver to create (this will allow the browser to access trello.com english page)
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en"); 
		//create webdriver
		WebDriver driver = new ChromeDriver(options);
		//go to url
		driver.get("https://trello.com/");
		
		//1st webpage "Trello"
		//Wait for home page load 
		WebDriverWait waitHomepageLoad = new WebDriverWait(driver,30);
		waitHomepageLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		//"Log In" button which actually is a link with CSS.
		String loginLinkValue = "Log In";
		WebElement loginButton = driver.findElement(By.linkText(loginLinkValue));
		loginButton.click();
		
		//2nd webpage "Log In to Trello" login attepmt.
		//Wait for login page load 
		WebDriverWait waitLogpageLoad = new WebDriverWait(driver,30);
		waitLogpageLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		//"Email (or username)" input, Text Type (Field).
		String userId = "user";
		WebElement userField = driver.findElement(By.id(userId));
		userField.sendKeys("jesuseltester@gmail.com");
		//"Password" input, Password Type (Field).
		String passId = "password";
		WebElement passField = driver.findElement(By.id(passId));
		passField.sendKeys("Trellopass2017");
		//"Log In" button, Submit Type.
		String logButtonId = "login";
		WebElement loginButton2 = driver.findElement(By.id(logButtonId));
		loginButton2.click();
		//"There isn't an account for this email" error message.
		String errorId = "error";
		WebDriverWait waitErrMsg = new WebDriverWait(driver, 5);
		//WebElement errMsg = driver.findElement(By.id(errorId));
		//waitErrMsg.until(ExpectedConditions.visibilityOf(errMsg));
		waitErrMsg.until(ExpectedConditions.visibilityOfElementLocated(By.id(errorId)));
		//"Create a Trello account" link
		String signupId = "signup";
		WebElement signupLink = driver.findElement(By.id(signupId));
		signupLink.click();

		//3rd webpage "Create a Trello Account" account creation.
		//Wait for Signup page load
		WebDriverWait waitSignupLoad = new WebDriverWait(driver,30);
		waitSignupLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		//"Name" input, Text Type (Field).
		String nameId = "name";
		WebElement nameField = driver.findElement(By.id(nameId));
		nameField.sendKeys("Jesus Eltester");
		//"Email" input, Email Type (Field).
		String emailId = "email";
		WebElement emailField = driver.findElement(By.id(emailId));
		emailField.sendKeys("jesuseltester@gmail.com");
		//"Password" input, Password Type (Field).
		String passwordId = "password";
		WebElement passwordField = driver.findElement(By.id(passwordId));
		passwordField.sendKeys("Trellopass2017");
		//"I accept the Terms of Service and Privacy Policy" Checkbox Type
		String acceptTermsId = "accept-tos";
		WebElement acceptChkbox = driver.findElement(By.id(acceptTermsId));
		if (!acceptChkbox.isSelected())
		{
			acceptChkbox.click();
		}
		//"Create New Account" button, Button Type.
		String signupId2 = "signup";
		WebDriverWait waitSignupEnable = new WebDriverWait(driver,5);
		WebElement signupButton = waitSignupEnable.until(ExpectedConditions.elementToBeClickable(By.id(signupId2)));
		signupButton.click();
		
		//4th webpage "Boards | Trello" logout from created account.
		//Wait for Main user page load
		WebDriverWait waitMainpageLoad = new WebDriverWait(driver,30);
		waitMainpageLoad.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		//"Open Member Menu"
		String memberMenuXPath = "//*[@id=\"header\"]/div[4]/a[4]/span/span[1]"; 
		WebElement openMemberMenu= driver.findElement(By.xpath(memberMenuXPath));
		openMemberMenu.click();
		//
		String logoutLinkValue = "Log Out";
		WebElement logoutListItem = driver.findElement(By.linkText(logoutLinkValue));
		logoutListItem.click();
		
		System.out.println("End Of TestScript Run");		
	}

}
