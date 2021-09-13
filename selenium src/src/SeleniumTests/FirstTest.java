package SeleniumTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumStuff\\chromedriver_win32\\chromedriver.exe");
		
//		loginTest();
		
		addUserTest();
		
	}
	
	public static void loginTest() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:4200/");
		Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys("bivy");
		Thread.sleep(1000);
		driver.findElement(By.name("password")).sendKeys("1234bi");
		
		Thread.sleep(1000);
		
		driver.findElement(By.name("loginButton")).click();
		
		Thread.sleep(1000);
	
		driver.findElement(By.name("logoutButton")).click();
		
		Thread.sleep(1000);
		
		driver.close();
	}
	
	
	public static void addUserTest() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:4200/");
		Thread.sleep(1000);		
		driver.findElement(By.name("addUserButton")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys("test");
		Thread.sleep(1000);
		driver.findElement(By.name("password")).sendKeys("test");		
		Thread.sleep(1000);
		driver.findElement(By.name("firstname")).sendKeys("test");
		Thread.sleep(1000);
		driver.findElement(By.name("lastname")).sendKeys("test");		
		Thread.sleep(1000);
		driver.findElement(By.name("createUserButton")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys("test");
		Thread.sleep(1000);
		driver.findElement(By.name("password")).sendKeys("test");		
		Thread.sleep(1000);		
		driver.findElement(By.name("loginButton")).click();	
		Thread.sleep(1000);	
		driver.findElement(By.name("logoutButton")).click();
		
		Thread.sleep(1000);		
		driver.close();
	}
	
	

}
