package com.dal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.models.Egm;

public class FilloExcelGateway implements IExcelGateway {

	@Override
	public HashMap<String, LinkedList<Egm>> getEgmMap(String filePath, String sheetName) throws FilloException, ParseException {
		// TODO Auto-generated method stub
		ArrayList<Egm> egmList = new ArrayList<Egm>();
		HashMap<String, LinkedList<Egm>> egmMap = new HashMap<String, LinkedList<Egm>>();
		//try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(filePath);
			String strQuery = "Select * from Sheet1 where Bol_reference<>''";
			Recordset recordset = connection.executeQuery(strQuery);

			while (recordset.next()) {
				Egm egm = new Egm();
				//egm.setSerialNo(Integer.parseInt(recordset.getField("SL")));
				egm.setBlNo(recordset.getField("Bol_reference"));
				//egm.setMasterBL(recordset.getField("MASTER_BL"));
				//egm.setcWeight(Double.parseDouble(recordset.getField("CWEIGHT")));
				//egm.setCbm(Double.parseDouble(recordset.getField("CBM")));
			//	egm.setEfrNo(recordset.getField("EFR/BE NO"));
				//Date date = new SimpleDateFormat("dd-MMM-yy").parse(recordset.getField("BE DATE"));
				//String dateString = new SimpleDateFormat("dd/MM/yyyy").format(date);
				//egm.setDate(dateString);
				//egm.setOfficeCode(recordset.getField("BE OFFICE"));
				if (!egmMap.containsKey(egm.getBlNo())) {
					LinkedList<Egm> egmLinkedList = new LinkedList<Egm>();
					egmLinkedList.add(egm);
					egmMap.put(egm.getBlNo(), egmLinkedList);
				} else {
					LinkedList<Egm> egmLinkedList = egmMap.get(egm.getBlNo());
					egmLinkedList.add(egm);
				}
				// egmList.add(egm);

			}

			recordset.close();
			connection.close();
		//} 
		return egmMap;
	}

	

}
