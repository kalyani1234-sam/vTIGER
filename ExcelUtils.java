package commonUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	public String getDataFRomExcel(String sheetName,int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis= new FileInputStream("src\\test\\resources\\data2.xlsx");
		Workbook w1 = WorkbookFactory.create(fis);
		Sheet sheet = w1.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);  //enter coloumn number
		String value = cell.getStringCellValue();
		
		
		return value;
	}
	
	public  String writeDataInExcel(String sheetName,int rowNum,int colNum,String val) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis= new FileInputStream("C:\\Users\\ADMIN\\Desktop\\data2.xlsx");
		Workbook w1 = WorkbookFactory.create(fis);
		w1.getSheet("Sheet1");
		Sheet sh = w1.getSheet(sheetName);
		Row row = sh.createRow(rowNum);
		Cell cell = row.createCell(colNum);
		cell.setCellValue(val);
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\ADMIN\\Desktop\\data2.xlsx"); //to write data in excel sheet
		
		w1.write(fos);
		
		return val;
		
		
	}

	public  String writeDataInExcelvTiger(String sheetName,int rowNum,int colNum,String val) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis= new FileInputStream("C:\\Users\\ADMIN\\Desktop\\framework\\vtigerData.xlsx");
		Workbook w1 = WorkbookFactory.create(fis);
		w1.getSheet("Sheet1");
		Sheet sh = w1.getSheet(sheetName);
		Row row = sh.createRow(rowNum);
		Cell cell = row.createCell(colNum);
		cell.setCellValue(val);
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\ADMIN\\Desktop\\framework\\vtigerData.xlsx"); //to write data in excel sheet
		
		w1.write(fos);
		
		return val;
		
		
	}
	public String getDataFRomExcelVTIGER(String sheetName,int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis= new FileInputStream("C:\\Users\\ADMIN\\Desktop\\framework\\vtigerData.xlsx");
		Workbook w1 = WorkbookFactory.create(fis);
		Sheet sheet = w1.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);  //enter coloumn number
		String value = cell.getStringCellValue();
		
		
		return value;
	}
}
