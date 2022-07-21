package assignment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LinkNavigation {
	WebDriver driver;
	String baseURL = "https://opensource-demo.orangehrmlive.com/";
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		driver = new ChromeDriver();
		driver.get(baseURL);
//		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	}
	
	@Test
	public void getTitle() throws InterruptedException {
		List <WebElement> links = driver.findElements(By.xpath("//div[@id='social-icons']/a"));
		for(int i=0;i<links.size();i++) {
			driver.navigate().to(links.get(i).getAttribute("href"));
			String title = driver.getTitle();
			System.out.println(title);
			Thread.sleep(2000);
			driver.navigate().back();
		}
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
