package testscripts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;


public class HelloWorld {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		String baseurl="https://10.2.74.204/#/login";
		/*String expectedTitle="Welcome: Mercury Tours";
		String actualTitle = "";*/
		
		driver.get(baseurl);
		//actualTitle=driver.getTitle();
		
       
		driver.findElement(By.id("inputUsername")).sendKeys("admin");
		driver.findElement(By.id("inputPassword3")).sendKeys("passw0rd");
		driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).submit();
		//driver.findElement(By.name("submit")).click();
		
		//System.out.println("Home page reached");Home
		//driver.findElement(By.tex("Home")).click();
		  //driver.findElements By.xpath("//*//[contains(text(),'Home')]"));
		
    	System.out.println("Test Failed");
    	driver.close();
    }
		//String newurl="https://10.2.74.204/#/pages/home";
		/*String nUrl= driver.getCurrentUrl();
		System.out.println(nUrl);
		
		if (nUrl.equalsIgnoreCase("https://10.2.74.204/#/pages/home"))
				{
			System.out.println("Test Passed!");
    } else {
        System.out.println("Test Failed");
    }
		//boolean sample = driver.getPageSource().contains("Home");
		//System.out.println(sample);
		//driver.wait(5000);
		
		*/
		
	}


