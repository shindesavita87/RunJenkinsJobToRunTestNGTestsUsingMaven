package testngsuite;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

public class DataProviderTest {

	private static WebDriver driver;

	@DataProvider(name = "login")

	public static Object[][] credentials() {

		// The number of times data is repeated, test will be executed the same no. of
		// times

		// Here it will execute two times

		return new String[][] { { "Savita20", "@Savita20" }, { "mercury", "mercury" } };

	}

	@BeforeMethod
	public void setUP() {
		System.setProperty("webdriver.chrome.driver", "ssrc/test/java/resources/chromedriver");

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://demo.guru99.com/test/newtours/");
	}

	
	// Here we are calling the Data Provider object with its Name

	@Test(dataProvider = "login",groups = { "sanity" ,"smoke"})

	public void test(String sUsername, String sPassword) {

		driver.findElement(By.name("userName")).sendKeys(sUsername);

		driver.findElement(By.name("password")).sendKeys(sPassword);

		driver.findElement(By.name("submit")).click();

		Assert.assertEquals(driver.getTitle(), "Login: Mercury Tours", "Login failed..");

		driver.findElement(By.linkText("SIGN-OFF")).click();

		driver.findElement(By.linkText("Home")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}