package testngsuite;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class BestExDependTestNgOnMethod {

    WebDriver driver;
    SoftAssert softAssert;

    @BeforeClass
    public void setup() {
    	System.setProperty("webdriver.chrome.driver","src/test/java/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/test/newtours/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {

        driver.close();
    }

    @Test(priority = 0,groups = { "smoke" })
    public void login() {
    	driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("mercury");
        driver.findElement(By.name("password")).sendKeys("mercury");
        driver.findElement(By.name("submit")).click();
    }

    @Test(priority = 1,groups = { "sanity" }, dependsOnMethods="login")
    public void afterAllSoftAssertion() {
    	
    	 String title=driver.getTitle();
         Assert.assertEquals(title,"Login: Mercury Tours","Asset is used to verify expected and actual strings are match");
    }
}