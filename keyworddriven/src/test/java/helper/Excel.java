package helper;
/*
 * It is generic helper class which will read and write data into any given excel file
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel {
	Workbook book;
	Sheet sh;
	WritableWorkbook wbook;
	WritableSheet wsh;
	String path;
	public Excel(String folderName, String fileName) {
		path = new StringBuilder(System.getProperty("user.dir") + File.separator + folderName + File.separator + fileName).toString();
		System.out.println(path);
	}
	
	//set an excel file to read the data
	public void setExcel(String sheetName) {
		try {
			FileInputStream fis = new FileInputStream(path);
			book = Workbook.getWorkbook(fis);
			sh = book.getSheet(sheetName);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//count number of rows
	public int rowCount() {
		return sh.getRows();
	}
	
	//count number of columns
	public int columnCount() {
		return sh.getColumns();
	}
	
	//read data from a cell
	public String readData(int row,int column) {
		return sh.getCell(column, row).getContents();
	}
	
	//set an excel file to write the data
	public void setOutputExcel(String sheetName) {
		try {
			FileInputStream fis =  new FileInputStream(path);
			book = Workbook.getWorkbook(fis);
			FileOutputStream fos = new FileOutputStream(path);
			wbook = Workbook.createWorkbook(fos, book);
			wsh = wbook.getSheet(sheetName);	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setOutputExcel(String oFileName, String sheetName) {
		try {
			FileInputStream fis =  new FileInputStream(path);
			book = Workbook.getWorkbook(fis);
			FileOutputStream fos = new FileOutputStream(path);
			wbook = Workbook.createWorkbook(fos, book);
			wsh = wbook.getSheet(sheetName);	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//write data into a cell
	public void writeData(int row, int column, String data) {
		try {
			wsh.addCell(new Label(column, row, data));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//save and boik the workbook
	public void saveWorkBook() {
		try {
			wbook.write(); //save the data
			wbook.close();
			book.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
