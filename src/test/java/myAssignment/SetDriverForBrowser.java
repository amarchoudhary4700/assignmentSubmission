package myAssignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SetDriverForBrowser {
	

	@BeforeSuite
	public WebDriver setUp(String browser) {
		WebDriver driver = null;
		switch(browser) {
			case "Chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
		}
		
		return driver;
				
	} 


}
