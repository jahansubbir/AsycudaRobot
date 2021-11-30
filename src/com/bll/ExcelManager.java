package com.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dal.IExcelGateway;
import com.models.Egm;

public class ExcelManager {

	IExcelGateway excelGateway;
	public ExcelManager(IExcelGateway excelGateway) {
		// TODO Auto-generated constructor stub
		this.excelGateway=excelGateway;
	}
	public Map<String,LinkedList<Egm>> GetEgmList(String filePath,String sheetName) throws Exception {
		HashMap<String, LinkedList<Egm>> egmList=this.excelGateway.getEgmMap(filePath, sheetName);
		
		return egmList;
	}
}
