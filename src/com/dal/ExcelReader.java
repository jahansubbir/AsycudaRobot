package com.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.models.Egm;

public class ExcelReader {

	public ArrayList<Egm> GetEgmListFromExcel(String filePath, String sheetName) throws IOException {
		ArrayList<Egm> egmList = new ArrayList<Egm>();

		// creating a new File
		File file = new File(filePath);

		// obtain bytes from the file
		FileInputStream inputStream = new FileInputStream(file);

		// Creating a workbook Instance
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet worksheet = workbook.getSheet(sheetName);
		Iterator<Row> iterator = worksheet.iterator();// itrating over excel
		Row columnRow = iterator.next();
		Iterator<Cell> columnIterator = columnRow.cellIterator();// iterating over each column
/*		while (columnIterator.hasNext()) {

			Cell column = columnIterator.next();
			switch(column.getStringCellValue()) {
			case "BL/"
			}
		}*/

		while (iterator.hasNext()) {

			Row row = iterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();// iterating over each column

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

			}

		}
		return null;

	}
}
