package testscripts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;


public class HelloWorld {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		String baseurl="http://demo.guru99.com/test/newtours/";
		/*String expectedTitle="Welcome: Mercury Tours";
		String actualTitle = "";*/
		
		driver.get(baseurl);
		//actualTitle=driver.getTitle();
		
       
		driver.findElement(By.name("userName")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("submit")).click();
		//driver.wait(5000);
		
		//System.out.println(driver.findElements(By.xpath("//*[contains(text(),'Login Successfully')]")));
		
		boolean sample = driver.getPageSource().contains("Login Successfully");
		//System.out.println(sample);
		//driver.findElement(By.xpath("//div[h3[text()='Login Successfully']]"));
		//String actualTitle = "";
		
		
		if (sample==true){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
		
		driver.close();
	}

}
