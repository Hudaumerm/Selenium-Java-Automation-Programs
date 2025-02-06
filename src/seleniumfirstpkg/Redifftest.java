package seleniumfirstpkg;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Redifftest {
	
	WebDriver driver;
	
	@BeforeTest
	public void Setup() {
		driver=new EdgeDriver();
		driver.get("https://register.rediff.com/register/register.php?FormName=user_details");
	}

	@Test
	public void handleusingrobotclass() {
	WebElement fullname=driver.findElement(By.xpath("/html/body/center/form/div/table[2]/tbody/tr[3]/td[3]/input"));
	fullname.sendKeys("Hudhaumer");

		WebElement rediffid=driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[7]/td[3]/input[1]"));

		Actions act=new Actions(driver);
			act.keyDown(fullname, Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
	act.keyDown(fullname, Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL);
	act.keyDown(rediffid, Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL);
		
		act.build().perform();


	}
	
	@Test
	public void dropdownselect() {
		
		WebElement day=driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[1]"));
		Select daydetails=new Select(day);
		daydetails.selectByVisibleText("02");
		
		
	}
	@Test
	public void btnText() throws IOException {
		WebElement btn=driver.findElement(By.id("register"));
		String btext=btn.getDomAttribute("value");
//		String atext="Create My account >>";
//		assert.assertEquals(btext, atext, btext);
		System.out.println(btext);
	}
	
}
