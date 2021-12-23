package testngwithxml;
 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
public class MultiBrowserWithParameterTag {
 
      private WebDriver driver;
 
	  @Parameters({ "browser", "url" })
      @BeforeClass
      public void beforeTest(String browser,String url) {
             if (browser.equalsIgnoreCase("firefox")) {
            	 System.setProperty("webdriver.gecko.driver", "src/testngwithxml/resources/geckodriver");
                    driver = new FirefoxDriver();
             } else if (browser.equalsIgnoreCase("chrome")) {
                    // Set Path for the executable file
                    System.setProperty("webdriver.chrome.driver","src/testngwithxml/resources/chromedriver");
                    driver = new ChromeDriver();
             }  else if (browser.equalsIgnoreCase("ie")) {
                 // Set Path for the executable file
                 System.setProperty("webdriver.ie.driver", "src\\resource\\IEDriverServer.exe");
                 driver = new InternetExplorerDriver();
	          }else if (browser.equalsIgnoreCase("opera")) {
	              // Set Path for the executable file
	              System.setProperty("webdriver.opera.driver","src\\resource\\operadriver.exe");
	              driver = new OperaDriver();
	          }else if (browser.equalsIgnoreCase("safari")) {
              // Set Path for the executable file
                 driver = new SafariDriver();
             } else {
                    throw new IllegalArgumentException("The Browser Type is Undefined");
             }
             
             driver.get(url);
             driver.manage().window().maximize();
      }
 
	  @Parameters({ "username", "password" })
	  @Test
      public void login(String username,String password) throws InterruptedException {
             // Enter UserName
          driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(username);
          driver.findElement(By.name("password")).sendKeys(password);
          driver.findElement(By.name("submit")).click();
 
          String title=driver.getTitle();
          Assert.assertEquals(title,"Login: Mercury Tours","Asset is used to verify expected and actual strings are match");
      }
 
      @AfterClass
      public void afterTest() {
            driver.close();
      }
}