package com.dal;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.models.Egm;

public class UpdateLogger {
	public static void LogStatus(String fileName, String sheetName, Egm egm) {
		try {
			StatusWriter writer=new StatusWriter();
			writer.WriteStatus(egm);
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(fileName);
			
			String query = "update Sheet1 set "+"\"Update Status\""+"='"+egm.getUpdateStatus()+"',Reason='"+egm.getErrorReason()+"' WHERE BL_FCR='"+egm.getBlNo()+"'";
			// Recordset recordset = connection.executeQuery(strQuery);
			connection.executeUpdate(query);
			connection.close();
		}
		catch (FilloException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
