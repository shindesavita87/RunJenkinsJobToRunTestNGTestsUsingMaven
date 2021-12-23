package testngsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;


public class SoftAssertDemoTestNG {

    WebDriver driver;

    @BeforeClass
    public void setup() {

        driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/test/newtours/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {

        driver.close();
    }

    @Test(priority = 0)
    public void login() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:", "Trying to verify after login page even I am on Home page.");

        driver.findElement(By.linkText("REGISTER")).click();

        softAssert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours", "Trying to verify Home page even I am on Register page.");

        driver.findElement(By.linkText("Home")).click();

        softAssert.assertEquals(driver.getTitle(), "Register: Mercury Tours", "Trying to verify Register page even I am on Home page.");

        softAssert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours", "Trying to verify correct Home page.");

        System.out.println("This message is just to show you even after assert failed this line will get executed because we used soft assertions.");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void afterAllSoftAssertion() {
        Assert.assertEquals("Hi","Hi");
    }
}