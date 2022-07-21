package assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MultipleLogin {
	WebDriver driver;
	String baseURL = "https://opensource-demo.orangehrmlive.com/";
	String expectedURL = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
//		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider="DP")
	public void login(String username, String password) throws InterruptedException {
		WebElement usernameField = driver.findElement(By.id("txtUsername"));
		WebElement passwordField = driver.findElement(By.id("txtPassword"));
		WebElement submit = driver.findElement(By.name("Submit")); 
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		submit.click();
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, expectedURL, "Unable to Login");
		driver.findElement(By.partialLinkText("Welcome")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Logout")).click();
	}
	
	@DataProvider(name="DP")
	public Object[][] dataObject(){
		return new Object[][]{
				{"abhishek", "password"},{"Admin", "admin123"}, {"qrat", "abc12"}, {"Aaliyah.Haq", "Aaliyah@123#"}
		};
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
