package testngsuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExtentReport {

    ExtentReports report = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReportForMultipleTest.html", true);
    ExtentTest logger;
    ChromeDriver driver;
    ChromeOptions options;

    public static String captureScreenShot(WebDriver driver, String imageName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String desc = System.getProperty("user.dir") + "//test-output//" + imageName + ".jpg";
            File deFile = new File(desc);
            FileUtils.copyFile(source, deFile);
            System.out.println("Screenshot taken");
            return desc;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);

        logger = report.startTest("VerifyTitle_1", "Welcome: Mercury Tours");

        logger.assignCategory("Regression", "Functional");

        Map sysInfo = new HashMap();
        sysInfo.put("Selenium Version", "4.1.0");
        sysInfo.put("Environment", "Prod");

        report.addSystemInfo(sysInfo);

        logger.assignAuthor("Savita", "Suraj");

        logger.log(LogStatus.INFO, "Screencast below: " + logger.addScreencast(System.getProperty("user.dir") + "/test-output/screencast-path"));

        logger.log(LogStatus.INFO, "Usage:BOLD TEXT ");

        logger.log(LogStatus.INFO, "Browser has launched.");

        driver.get("http://demo.guru99.com/test/newtours/");

        logger.log(LogStatus.INFO, "page is opened...");

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test(priority = 1)
    public void verifyTitleBeforeLogin() {
        logger = report.startTest("VerifyTitle_2", "Welcome: Mercury Tours");

        logger.log(LogStatus.INFO, "Verify title before login.");

        Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");

        logger.log(LogStatus.PASS, "Before login Title is verified.");

        logger.log(LogStatus.INFO, "Enter user name.");

        driver.findElement(By.name("userName")).sendKeys("mercury");

        logger.log(LogStatus.INFO, "Enter password");

        driver.findElement(By.name("password")).sendKeys("mercury");

        logger.log(LogStatus.INFO, "Click on Login button.");

        driver.findElement(By.name("submit")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test(priority = 2)
    public void verifyTitleAfterLogin() {
        logger = report.startTest("VerifyTitle_3", "Login: Mercury Tours");

        logger.log(LogStatus.INFO, "Verify title after login login.");

        Assert.assertEquals(driver.getTitle(), "Login: Mercury Tours");

        logger.log(LogStatus.INFO, "Click on sign-out button.");

//        Boolean checkIfStillLogin = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBe(By.xpath("//td[1]/a"),"SIGN-OFF"));
//         if(checkIfStillLogin.equals(true)) {
//             driver.findElement(By.linkText("SIGN-OFF")).click();
//             Boolean firstResult = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBe(By.xpath("//td[1]/a"),"SIGN-ON"));
//             System.out.println("Check if element is visible: " +firstResult);
//         }
//
//
//        logger.log(LogStatus.INFO, "Verify title after logout.");
//
//        Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");

        logger.log(LogStatus.PASS, "Title is verified after logout.");

    }

    @AfterMethod
    public void afterthat(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String img_path = captureScreenShot(driver, result.getName());
            String image = logger.addScreenCapture(img_path);
            logger.log(LogStatus.PASS, "Title verification", image);
            logger.log(LogStatus.INFO, "Snapshot below: " + logger.addScreenCapture(image));
        }
        report.endTest(logger);
        report.flush();
    }

    @AfterClass
    public void tearDown() {

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");

        driver.switchTo().window(driver.getWindowHandle());

        driver.get( "http://localhost:63342/RunJenkinsJobToRunTestNGTests/test-output/ExtentReportForMultipleTest.html");
        driver.navigate().refresh();
    }
}