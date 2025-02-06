package seleniumfirstpkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Parameterizedfirst {
	WebDriver driver;
	
	@BeforeTest
	public void Setup() {
		driver=new EdgeDriver();
		driver.get("https://www.google.com");
	}
@Parameters("b")
	@Test
	public void test1(String b) {
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys(b);
		search.submit();
	}
}
