package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path; 
	
	public ExcelUtils(String path) //use path as constructor so that it can be passed by other class
	{
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public int getcolCount(String sheetName, int rowNum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int colCount = ws.getRow(rowNum).getLastCellNum();
		wb.close();
		fi.close();
		return colCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		cell = ws.getRow(rowNum).getCell(colNum);
		
		String data;
		data = cell.toString();
		wb.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int intColNum, String data) throws IOException
	{
		File file = new File(path);
		if(!file.exists()) //if file doesn't exist create new file
		{
			wb=new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		if(wb.getSheetIndex(sheetName)==-1) //if sheet doesn't exist create new sheet
		{
			wb.createSheet(sheetName);
		}
		ws = wb.getSheet(sheetName);
		if(ws.getRow(rowNum)==null) //if row doesn't exist create new row
		{
			ws.createRow(rowNum);
		}
		cell = ws.getRow(rowNum).createCell(intColNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void setGreenColor(String sheetName, int rowNum, int intColNum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(intColNum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void setRedColor(String sheetName, int rowNum, int intColNum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(intColNum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
