package testngwithxml;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class SoftAssertionAndGroupsDemo {

    WebDriver driver;

    @BeforeClass
    public void setup() {

        driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/test/newtours/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {

        driver.close();
    }

    @Test(priority = 0,groups = { "smoke" ,"sanity"})
    public void login() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:", "Trying to verify after login page even I am on Home page.");

        driver.findElement(By.linkText("REGISTER")).click();

        softAssert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours", "Trying to verify Home page even I am on Register page.");

        driver.findElement(By.linkText("Home")).click();

        softAssert.assertEquals(driver.getTitle(), "Register: Mercury Tours");

        softAssert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours", "Trying to verify correct Home page.");

        softAssert.assertAll();
    }

    @Test(priority = 1,groups = { "sanity" })
    public void afterAllSoftAssersion() {
        Assert.assertEquals("Hi","Hi");
        System.out.print("This is sanity test..");
    }
}