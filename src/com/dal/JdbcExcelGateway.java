package com.dal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.models.Egm;

public class JdbcExcelGateway implements IExcelGateway {
@Override	
public HashMap<String, LinkedList<Egm>> getEgmMap(String filePath, String sheetName) {

		ArrayList<Egm> egmList = new ArrayList<Egm>();
		try {
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};" + "Dbq=" + filePath);
			PreparedStatement s = connection.prepareStatement("SELECT * FROM [EGM$]");
			//s.setString(1, "Jul-2013");
			s.execute();
			ResultSet rs = s.getResultSet();
			if (rs != null) {
				while (rs.next()) {
				//	System.out.println(rs.getInt("AllTXN_Issued"));
					Egm egm=new Egm();
					egm.setBlNo(rs.getString("BL/FCR"));
					egm.setEfrNo(rs.getString("EFR/BE NO"));
					egm.setDate(rs.getString("BE DATE"));
					egm.setOfficeCode(rs.getString("BE OFFICE"));
					egmList.add(egm);

				}
			}
			s.close();

			connection.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			// TODO: handle exception
		}
		//return egmList;
		return null;
	}

}
