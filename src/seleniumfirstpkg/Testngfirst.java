package seleniumfirstpkg;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testngfirst {
	
	WebDriver driver;
	@BeforeTest
	public void setup() {
		driver = new EdgeDriver();
		System.setProperty("webdriver.chrome.driver", "path_to_undetected_chromedriver");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.google.com");
	}
	
	@Test(priority=0)
	public void screenshotMethodelement() throws IOException {
		WebElement gsearch=driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]"));
		File ss=gsearch.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(ss, new File("./Screenshot/google.png"));
		
	}

	@Test(priority=1)
	public void searchon()
	{
		WebElement search=driver.findElement(By.xpath("//textarea[@name='q']"));
		search.sendKeys("selenium");
		search.submit();
	}
	
//	@Test
//	public void captcharemove() {
//		
//	}
	
	@Test(priority=2)
	public void linkvalidation()
	{
		List <WebElement> l=driver.findElements(By.tagName("a"));
		System.out.println(l.size());
		
		for( WebElement e:l)
		{
			String link=e.getDomAttribute("href");
			System.out.println("Links are:"+link);
			verify(link);
			
			
			
		}
		
	}
	private void verify(String link) {
		// TODO Auto-generated method stub
		try {
			URL u=new URL(link);
			HttpURLConnection con=(HttpURLConnection)u.openConnection();
			con.connect();
			if(con.getResponseCode()==100) {
				System.out.println("Informational"+link);
				
			}
			else if(con.getResponseCode()==200) {
				System.out.println("Success"+link);
				
			}
			else if(con.getResponseCode()==300) {
				System.out.println("Redirection"+link);
				
			}
			else if(con.getResponseCode()==400) {
				System.out.println("Client error"+link);
				
			}
			else{
				System.out.println("Server error"+link);
				
			}
		}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		@Test(priority=3)
		public void screenshotMethod() throws IOException {
			File ss=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(ss, new File("C://Screenshot//Google.png"));
			
		}
		
	

	@AfterTest
	public void closeb(){
		driver.quit();
	}
}
