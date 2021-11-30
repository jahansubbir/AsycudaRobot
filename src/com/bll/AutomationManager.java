package com.bll;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.util.DateFormatConverter;


import com.factory.RobotFactory;
import com.models.Egm;

public class AutomationManager {

	private RobotFactory robotFactory;
	String updateFilePath;

	public AutomationManager(RobotFactory robotFactory) {
		// TODO Auto-generated constructor stub
		this.robotFactory = robotFactory;
		this.updateFilePath = updateFilePath;
	}

	public void Automate(Map<String, LinkedList<Egm>> egmMap, String xmlFolderPath, String updateFilePath) {

		/*
		 * for (Egm egm : egmList) { String
		 * xmlFilePath=xmlFolderPath+egm.getBlNo()+".xml";
		 * 
		 * robotFactory.Automate(xmlFilePath,egm.getOfficeCode(),
		 * egm.getEfrNo(),egm.getDate()); }
		 */
		if (egmMap != null) {

			
			for (LinkedList<Egm> egmList : egmMap.values()) {
				List<Egm> list = egmList;
				double cbm = list.stream().mapToDouble(a -> a.getCbm()).sum();
				double cWeight = list.stream().mapToDouble(a -> a.getcWeight()).sum();
				DecimalFormat f = new DecimalFormat("##.00");
				cWeight = Double.parseDouble(f.format(cWeight));
				cbm = Double.parseDouble(f.format(cbm));
				Egm egm = egmList.getFirst();
				// passing total containers cWeight and CBM
				egm.setcWeight(cWeight);
				egm.setCbm(cbm);
				// passing total container number against the BL
				egm.setTotalContainers(list.size());
				String xmlFilePath = xmlFolderPath + egm.getBlNo() + ".xml";
				try {

					robotFactory.Automate(egm, xmlFilePath, cbm, cWeight, updateFilePath);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
