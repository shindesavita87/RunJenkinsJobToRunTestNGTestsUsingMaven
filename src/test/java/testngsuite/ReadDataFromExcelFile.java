package testngsuite;

import java.io.*;

import org.apache.poi.hssf.usermodel.*;

public class ReadDataFromExcelFile {

	public static void main(String[] args) {

		try {

			FileInputStream file = new FileInputStream(new File("src/test/java/resources/Controller.xls"));

			HSSFWorkbook workbook = new HSSFWorkbook(file);

			HSSFSheet sheet = workbook.getSheetAt(0);

			String heading = sheet.getRow(0).getCell(0).getStringCellValue();

			String searchText1 = sheet.getRow(1).getCell(0).getStringCellValue();

			String searchText2 = sheet.getRow(2).getCell(0).getStringCellValue();

			System.out.println("Heading is:" + heading);

			System.out.println("Search Text 1 is:" + searchText1);

			System.out.println("Search Text 2 is:" + searchText2);

			file.close();

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}