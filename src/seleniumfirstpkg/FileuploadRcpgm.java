package seleniumfirstpkg;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FileuploadRcpgm {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup()  {
		driver =new EdgeDriver();
		driver.get("https://www.ilovepdf.com/pdf_to_word");
		
	}
	@Test
	public void test1() throws AWTException {
		driver.findElement(By.xpath("//*[@id=\"pickfiles\"]")).click();
		fileUpload("C:/                                                                        Users/hudau/Downloads/sample-1.pdf");
		
	}
		
		
		
	

	public void fileUpload(String f) throws AWTException {
		// TODO Auto-generated method stub
		StringSelection strselection = new StringSelection(f);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strselection, null);
		
		Robot robot=new Robot();
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		
		robot.delay(3000);
		
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		
				
	}

}
