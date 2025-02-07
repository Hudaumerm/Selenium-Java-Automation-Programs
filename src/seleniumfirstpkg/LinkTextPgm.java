package seleniumfirstpkg;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LinkTextPgm {

	
	WebDriver driver;

	@BeforeTest
	public void setUp() {
	ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		driver=new ChromeDriver(options);
		driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();	
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get("https://testautomationpractice.blogspot.com/");
	    
	}
	
	@Test(priority=1,enabled=true)
	public void linkTestPrinting() {
		WebElement linkApple=driver.findElement(By.linkText("Apple"));
		
		//scrolling page until we see the element
		JavascriptExecutor js=(JavascriptExecutor)driver;

		js.executeScript("arguments[0].scrollIntoView();", linkApple);
		
		js.executeScript("arguments[0].click();", linkApple);
		
		driver.navigate().back();
		
	}
	@Test(priority=2,enabled=true)
	public void partialLinkText() {
		JavascriptExecutor js=(JavascriptExecutor)driver;

		WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		
		
		WebElement linkLenovo=mywait.until(ExpectedConditions.elementToBeClickable((By.partialLinkText("Leno"))));
		js.executeScript("arguments[0].click();",linkLenovo );
		driver.navigate().back();

	}
	@Test(priority=3,enabled=true)
	public void handleTwoWindows() {
		
		Actions act=new Actions(driver);
		WebElement linkApple=driver.findElement(By.linkText("Apple"));

		//scrolling page until we see the element
	    JavascriptExecutor js=(JavascriptExecutor)driver;

	   js.executeScript("arguments[0].scrollIntoView();", linkApple);
		act.keyDown(Keys.CONTROL).click(linkApple).keyUp(Keys.CONTROL).perform();
		
		Set<String> winIds=driver.getWindowHandles();
		
		List<String> winIdList=new ArrayList(winIds);
		
		String parentId=winIdList.get(0);
		String childId=winIdList.get(1);
		
		driver.switchTo().window(childId);
		System.out.println("Apple page title="+driver.getTitle());
		
		

	}
	
	@Test(priority=4, dependsOnMethods="handleTwoWindows")
	public void multipleWindowHandle() throws IOException {
	    Actions act = new Actions(driver);
	    WebElement linkLearnmore = driver.findElement(By.cssSelector("a.button[aria-label='Learn more, iPhone 16 Pro']"));
	    
	    // Open the link in a new tab (Ctrl + Click)
	    act.keyDown(Keys.CONTROL).click(linkLearnmore).keyUp(Keys.CONTROL).perform();

	    // Get all window handles
	    List<String> winids = new ArrayList<>(driver.getWindowHandles());

	    // Iterate through all the window handles
	    for (String windowId : winids) {
	        String actualTitle = driver.switchTo().window(windowId).getTitle();

	        // If we find the window with the title "iPhone 16 Pro and iPhone 16 Pro Max - Apple"
	        if (actualTitle.equals("iPhone 16 Pro and iPhone 16 Pro Max - Apple")) {
	            // Take a screenshot
	            File sc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            FileHandler.copy(sc, new File("./Screenshot/" + actualTitle + ".png"));
	            
	            // Close the "iPhone 16 Pro and iPhone 16 Pro Max - Apple" window
	            driver.close();
	        }

	        // If we find the "Apple" window, close it
	        else if (actualTitle.equals("Apple")) {
	            driver.close();
	        }

	        // If we find the "Automation Testing Practice" window, switch to it
	        else if (actualTitle.equals("Automation Testing Practice")) {
	            driver.switchTo().window(windowId);
	        }
	    }

	    // After the loop, ensure to switch back to the parent window
	    driver.switchTo().window(winids.get(0));
	}

	@Test(priority=5, dependsOnMethods="multipleWindowHandle")
	public void checkBoxSelect() {
	    // Scroll to the checkbox element
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//div[@class='form-group']//div[4]")));

	    // Select the Monday checkbox
	    driver.findElement(By.xpath("//input[@id='monday']")).click();

	    // Get all checkboxes
	    List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));

	    // Print the number of checkboxes found
	    System.out.println("All checkbox data=" + checkboxes.size());

	    // Select checkboxes starting from index 3 to the end
	    for (int i = 3; i < checkboxes.size(); i++) {
	        checkboxes.get(i).click();
	    }

	    // Unselect all the selected checkboxes
	    for (WebElement checkbox : checkboxes) {
	        if (checkbox.isSelected()) {
	            checkbox.click();
	        }
	    }
	}

	@Test(priority=6)
	public void alerthandle()
	{
		
		WebElement alertBtn=driver.findElement(By.xpath("//button[@id='promptBtn']"));
		alertBtn.click();
		Alert a=driver.switchTo().alert();
		a.sendKeys("hudham");
		a.accept();
		
		//using explicit wait
		
		WebDriverWait alertwait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement alertBtn1=driver.findElement(By.xpath("//button[@id='alertBtn']"));
		alertBtn1.click();
		Alert normalalert=alertwait.until(ExpectedConditions.alertIsPresent());
		
		normalalert.accept();
		
		
	    
		
	
	}
	
	@Test(priority=7)
	public void handlenormalDropdown()
	{
		driver.findElement(By.xpath("//input[@id='comboBox']")).click();
		List<WebElement> dropdownItems=driver.findElements(By.xpath("//div[@id='dropdown']//div[@class='option']"));
		System.out.println("Total Items in scrolling dropdowns"+dropdownItems.size());
		
		for(WebElement items:dropdownItems)
		{
			System.out.println("Options are----"+items.getText());
			if(items.getText().equals("Item 90")) {
				items.click();
			}

		}
		
		
	}
	
	@Test(priority=8)
	public void sliderHandle() {
		
		try {
			
		
		Actions act=new Actions(driver);
		
		WebElement min_slider=driver.findElement(By.xpath("//body[1]/div[4]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[4]/div[3]/div[1]/aside[1]/div[1]/div[9]/div[1]/div[1]/span[1]"));
		System.out.println("current location="+min_slider.getLocation());
		act.dragAndDropBy(min_slider, 1, 1973).perform();
		System.out.println("after slide location="+min_slider.getLocation());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority=8)
	public void dragAndDroppgm() {
		
		try {
			
		
		Actions act=new Actions(driver);
		
		WebElement drag=driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement drop=driver.findElement(By.xpath("//div[@id='droppable']"));
 
		act.dragAndDrop(drag, drop).perform();
		
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@AfterTest()
	public void tearDown() {
		driver.quit();
	}
	
}
