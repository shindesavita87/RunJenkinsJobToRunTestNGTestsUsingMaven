package testngwithxml;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class SimpleTestNGPrograme {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "src/testngwithxml/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://demo.guru99.com/test/newtours/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldDoUnitTesting() {
        WebElement uName = driver.findElement(By.name("userName"));
        WebElement pwd = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.name("submit"));

        Actions actions = new Actions(driver);
        Action build = actions.moveToElement(uName).click(uName)
                .keyDown(Keys.SHIFT).sendKeys("s").keyUp(Keys.SHIFT)
                .sendKeys("savita20").keyDown(Keys.COMMAND)
                .sendKeys("a" + "c").keyUp(Keys.COMMAND).click(pwd)
                .keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).click(login).pause(200).build();

        build.perform();
    }
}
