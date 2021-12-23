//My practice program 4
package testngsuite;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WriteIntoFileTest {

	HSSFWorkbook hssfWorkbook;
	HSSFSheet sheet;
	LinkedHashMap<String, Object[]> table;
	ChromeDriver firefoxDriver;

	@BeforeClass
	public void Setup() {
		hssfWorkbook = new HSSFWorkbook();
		sheet = hssfWorkbook.createSheet("TestResults");

		table = new LinkedHashMap<String, Object[]>();
		table.put("1", new Object[] { "Sno", "TestCase", "Result" });
		System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");

		firefoxDriver = new ChromeDriver();
		firefoxDriver.get("http://demo.guru99.com/test/newtours/");
	}

	@Test
	public void shouldLogin() {
		Assert.assertEquals(firefoxDriver.getTitle(), "Welcome: Mercury Tours");
		table.put("2", new Object[] { "1", "I can reach login page", "Pass" });
	}

	@AfterClass
	public void fillData() {

		Set<String> strings = table.keySet();

		// rows loop
		int rowCount = 0;
		for (String key : strings) {
			HSSFRow row = sheet.createRow(rowCount);
			Object[] objects = table.get(key);

			// columns loop
			int cellNo = 0;
			for (Object obj : objects) {
				HSSFCell cell = row.createCell(cellNo);
				cell.setCellValue(obj.toString());
				cellNo++;
			}
			rowCount++;
		}

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File("src/testngwithxml/resources/Test.xls"));
			hssfWorkbook.write(fileOutputStream);
			fileOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void shouldDoTesting() {

	}
}
