package testngsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestNGInnovationCount {
    ChromeDriver driver;
    int count = 1;

    /**
     * TestNG invocationCount parameter will run test specified time
     */
    @Test(invocationCount = 2, description = "Print title..")
    public void verifyTitle() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        driver = new ChromeDriver();

        driver.get("http://demo.guru99.com/test/newtours/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("mercury");
        driver.findElement(By.name("password")).sendKeys("mercury");
        driver.findElement(By.name("submit")).click();

        String title=driver.getTitle();
        Assert.assertEquals(title,"Login: Mercury Tours","Asset is used to verify expected and actual strings are match");

        System.out.println("Printing title " + count + ": " + driver.getTitle());
        count++;
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}