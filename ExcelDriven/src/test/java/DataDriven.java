import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	// Identify TestCaes column by scanning the entire list row
	// once column is identified then scan entire Testcase column to identify
	// purchase test case row
	// after you grab the purchase row = pull the data of that row and feed into
	// test

	public ArrayList getData(String testCaseName) throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Administrator\\eclipse-workspace\\ExcelDriven\\src\\test\\resources\\demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// indentify testcases column by scanning the entire list row

				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();
				int k = 0;
				int column = 0;
				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						column = k;
					}
					k++;
				}
				
				// once column is identified then scan entire Testcase column to identify
				// purchase test case row
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						// after you grab the purchase row = pull the data of that row and feed into
						// test
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {

							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								a.add(c.getStringCellValue());

							} else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}

					}

				}

			}
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	}

}
