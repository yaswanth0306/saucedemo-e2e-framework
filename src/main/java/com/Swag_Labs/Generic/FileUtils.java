package com.Swag_Labs.Generic;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtils {
	
	public String readDataFromTestdataExcel(String sheet,int row,int cell) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.Testdatapath);
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		return data;
			
	}
	
	public String readDataFromCheckoutdetailsExcel(String sheet,int row,int cell) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.Checkoutpath);
		Workbook wb = WorkbookFactory.create(fis);
		Cell c = wb.getSheet(sheet).getRow(row).getCell(cell);
	    String data;

	    if (c.getCellType() == CellType.STRING) {
	        data = c.getStringCellValue();
	    } else if (c.getCellType() == CellType.NUMERIC) {
	        data = String.valueOf((int) c.getNumericCellValue()); // convert numeric to string
	    } else {
	        data = "";
	    }
	    return data;
	
}
}
