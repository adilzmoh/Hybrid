package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 
{
	Workbook wb;
	public ExcelFileUtil(String ExcelPath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(ExcelPath);
		wb = WorkbookFactory.create(fi);
	}
	public int rowCount(String sheetName) 
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
	public String getCellData(String sheetName,int row,int col) 
	{
		String data = "";
		if(wb.getSheet(sheetName).getRow(row).getCell(col).getCellType()==CellType.NUMERIC) 
		{
			int cellData = (int)wb.getSheet(sheetName).getRow(row).getCell(col).getNumericCellValue();
			data = String.valueOf(cellData);
		}
		else 
		{
			data = wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}
	public void setCellData(String sheetName,int row,int col,String status,String writeExcel) throws Throwable
	{
		Sheet ws = wb.getSheet(sheetName);
		Row rowNum = ws.getRow(row);
		Cell cell = rowNum.createCell(col);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass")) 
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(col).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail")) 
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(col).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked")) 
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(col).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
	}
/*public static void main(String[] args) throws Throwable 
{
	ExcelFileUtil xl = new ExcelFileUtil("D://sample sheet.xlsx");
	int rc = xl.rowCount("Emp");
	for(int i=1;i<=rc;i++)
	{
		String fname = xl.getCellData("Emp", i, 0);
		String mname = xl.getCellData("Emp", i, 1);
		String lname = xl.getCellData("Emp", i, 2);
		String eid = xl.getCellData("Emp", i, 3);
		System.out.println(fname+"  "+mname+"  "+lname+"  "+eid);
		//xl.setCellData("Emp", i, 4, "Pass", "D://Result1.xlsx");
		//xl.setCellData("Emp", i, 4, "Fail", "D://Result1.xlsx");
		xl.setCellData("Emp", i, 4, "Blocked", "D://Result1.xlsx");
	}
}*/
}













