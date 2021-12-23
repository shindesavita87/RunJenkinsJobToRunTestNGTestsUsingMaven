package testngsuite;

//http://www.java2s.com/Code/Jar/p/Downloadpoi37jar.htm
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class ReadExcelData {

    public static HSSFWorkbook WBook = null;
    public static HSSFSheet WSheet = null;
    public static Object[][] LoginData;
    public static HSSFRow Row;
    public static HSSFCell cell;
    public static String FilePath = "src/test/java/resources/Controller.xls";
    public static String SheetName1 = "Sheet1";
    public static HSSFSheet Sheet;
    String numWihoutDecimal;
    WebDriver driver;


    public static HSSFSheet DataSheet(String FilePath, String SheetName) {
        File file = new File(FilePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            WBook = new HSSFWorkbook(fis);
            WSheet = WBook.getSheet(SheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WSheet;
    }

    @DataProvider
    public static Object[][] getLoginData() throws Exception {

        Sheet = DataSheet(FilePath, SheetName1);
        int rowCount = Sheet.getLastRowNum();
        System.out.println(rowCount);
        int colCount = Sheet.getRow(0).getLastCellNum();
        System.out.println(colCount);

        LoginData = new Object[rowCount][colCount];

        for (int rCnt = 1; rCnt <= rowCount; rCnt++) {
            for (int cCnt = 0; cCnt < colCount; cCnt++) {
                LoginData[rCnt - 1][cCnt] = getCellData(SheetName1, rCnt, cCnt);
            }
        }
        return LoginData;
    }

    public static String getCellData(String Sheet, int row, int col) {
        try {
            int index = WBook.getSheetIndex(Sheet);

            WSheet = WBook.getSheetAt(index);
            Row = WSheet.getRow(row);
            if (Row == null)
                return "";

            cell = Row.getCell(col);
            if (cell == null)
                return "";

            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();

                case Cell.CELL_TYPE_BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());

                case Cell.CELL_TYPE_BLANK:
                    return "";

                case Cell.CELL_TYPE_ERROR:
                    return cell.getStringCellValue();

                case Cell.CELL_TYPE_NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());

                default:
                    return "Cell not found";

            }
        } catch (Exception e) {
            e.printStackTrace();
            return "row " + row + " or column " + col + " does not exist in xls";
        }
    }

    public static String convertStringToStringWithoutDecimal(String convertString) {
        String outputString;
        outputString = String.valueOf(convertString).split("\\.")[0];
        return outputString;
    }

    @BeforeClass
    public void setUP() {
    	System.setProperty("webdriver.chrome.driver","src/test/java/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://automationpractice.com/index.php");
    }

    @Test(dataProvider = "getLoginData")
    public void TC01_DoSingUp(String EmailAddress, String FirstName,
                              String LastName, String Password, String DateBirthDay,
                              String DateBirthMonth, String DateBirthYear,
                              String AddressFirstName, String AddressLastName, String Company,
                              String AddressLine1, String AddressLine2, String City,
                              String State, String PostalCode, String Country,
                              String AdditionalInfo, String HomePhone, String MobilePhone,
                              String Email) throws InterruptedException {
        driver.findElement(By.className("login")).click();

        String datef = (new SimpleDateFormat("hhmmsss")).format(new Date());
        driver.findElement(By.id("email_create")).sendKeys(datef + EmailAddress);
        driver.findElement(By.id("SubmitCreate")).click();
        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(FirstName);
        driver.findElement(By.id("customer_lastname")).sendKeys(LastName);
        driver.findElement(By.id("passwd")).sendKeys(Password);

        numWihoutDecimal = ReadExcelData.convertStringToStringWithoutDecimal(DateBirthDay);
        WebElement day = driver.findElement(By.id("days"));
        Select sel1 = new Select(day);
        sel1.selectByValue(numWihoutDecimal);

        numWihoutDecimal = ReadExcelData.convertStringToStringWithoutDecimal(DateBirthMonth);
        WebElement month = driver.findElement(By.id("months"));
        Select sel2 = new Select(month);
        sel2.selectByValue(numWihoutDecimal);

        numWihoutDecimal = ReadExcelData.convertStringToStringWithoutDecimal(DateBirthYear);
        WebElement year = driver.findElement(By.id("years"));
        Select sel3 = new Select(year);
        sel3.selectByValue(numWihoutDecimal);

        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();
        driver.findElement(By.id("firstname")).sendKeys(AddressFirstName);
        driver.findElement(By.id("lastname")).sendKeys(AddressLastName);
        driver.findElement(By.id("company")).sendKeys(Company);
        driver.findElement(By.id("address1")).sendKeys(AddressLine1);
        driver.findElement(By.id("address2")).sendKeys(AddressLine2);
        driver.findElement(By.id("city")).sendKeys(City);

        numWihoutDecimal = ReadExcelData.convertStringToStringWithoutDecimal(State);
        WebElement state = driver.findElement(By.id("id_state"));
        Select sel4 = new Select(state);
        sel4.selectByValue(numWihoutDecimal);

        numWihoutDecimal = ReadExcelData.convertStringToStringWithoutDecimal(PostalCode);
        driver.findElement(By.id("postcode")).sendKeys(numWihoutDecimal);

        numWihoutDecimal = ReadExcelData.convertStringToStringWithoutDecimal(Country);
        WebElement country = driver.findElement(By.id("id_country"));
        Select sel5 = new Select(country);
        sel5.selectByValue(numWihoutDecimal);

        driver.findElement(By.id("other")).sendKeys(AdditionalInfo);

        numWihoutDecimal = ReadExcelData.convertStringToStringWithoutDecimal(HomePhone);
        driver.findElement(By.id("phone")).sendKeys(numWihoutDecimal);

        numWihoutDecimal = ReadExcelData.convertStringToStringWithoutDecimal(MobilePhone);
        driver.findElement(By.id("phone_mobile")).sendKeys(numWihoutDecimal);

        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys(Email);
        driver.findElement(By.id("submitAccount")).click();

        WebElement element = driver.findElement(By.className("account"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));

        String accountName = element.getText();
        System.out.println("Account created successfully.. " + "\n"
                + "Expected: " + FirstName + " " + LastName + " " + "\n"
                + "Acual: " + accountName + "\n" + "Yes it passed..");
        Assert.assertEquals(FirstName + " " + LastName, accountName, "Account Not created successfully");

        WebElement element2 = driver.findElement(By.className("logout"));
        wait.until(ExpectedConditions.visibilityOf(element2));
        element2.click();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}