package usingTestngXML;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterLogin {
	WebDriver driver;
	String baseURL = "C";
	String expectedURL = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
//		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	@Parameters({"username", "password"})
	public void login(String username, String password) throws InterruptedException {
		System.out.println(username+" "+password);
		WebElement usernameField = driver.findElement(By.id("txtUsername"));
		WebElement passwordField = driver.findElement(By.id("txtPassword"));
		WebElement submit = driver.findElement(By.name("Submit")); 
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		submit.click();
		Thread.sleep(2000);
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		Assert.assertEquals(currentURL, expectedURL, "Unable to Login");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
