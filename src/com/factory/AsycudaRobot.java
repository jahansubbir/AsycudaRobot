package com.factory;


import org.sikuli.script.FindFailed;
import org.slf4j.ILoggerFactory;

import com.dal.UpdateLogger;
import com.models.Egm;

import main.SikuliRobot;

public class AsycudaRobot implements RobotFactory {

	SikuliRobot robot;
	UpdateLogger logger;

	public AsycudaRobot(SikuliRobot sikuliRobot, UpdateLogger logger) {
		// TODO Auto-generated constructor stub
		this.robot = sikuliRobot;
		this.logger = logger;
	}

	@Override
	public void Automate(Egm egm, String filePath, double cbm, double cWeight, String updateFilePath) {
		boolean sadExportAttached = true;
		boolean verified = false;
		// TODO Auto-generated method stub
		try {
			robot.CreateNewManifest(egm);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			egm.setUpdateStatus("Failed");
			egm.setErrorReason("Check if Connected to the Asycuda portal");
			UpdateLogger.LogStatus(updateFilePath, "Sheet1", egm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			robot.ImportFromXml(egm, filePath);
		} catch (FindFailed e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*
		 * try {
		 * 
		 * sadExportAttached = robot.FillSadExport(egm); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */		try {
			if (sadExportAttached) {
				verified = robot.VerifyDocument(egm, egm.getTotalContainers());
			} else {
				robot.cancelWaybill();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (sadExportAttached) {

				if (verified) {
					robot.Submit(egm);
					Thread.sleep(5000);
				} else {
					robot.cancelWaybill();
				}
			}
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception

		}
		// this.logger.LogStatus(updateFilePath, "Sheet1", egm.getUpdateStatus(),
		// egm.getErrorReason(), egm.getBlNo());
	}

}
