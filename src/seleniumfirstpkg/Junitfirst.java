package seleniumfirstpkg;


import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Junitfirst {

	WebDriver driver;
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		}
	
	@Test
	public void titleverification() {
		String title=driver.getTitle();
		String actual="Google";
		if(title.equalsIgnoreCase(actual)) {
			System.out.println("pass");
		}
		else {
			System.out.println("fail");

		}
	}
	
	
	
	@After
	public void close() {
		driver.quit();
	}
}
