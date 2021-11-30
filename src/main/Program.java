package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import org.bouncycastle.jcajce.provider.symmetric.Threefish;

import org.sikuli.script.FindFailed;

import com.bll.AutomationManager;
import com.bll.ExcelManager;
import com.dal.FilloExcelGateway;
import com.dal.UpdateLogger;
import com.factory.AsycudaRobot;
import com.factory.RobotFactory;
import com.kenai.jffi.Main;
import com.ui.AsycudaUI;


public class Program {

	public static String root;// = System.getProperty("user.dir") + "\\bin\\";
	public static String excelFilePath;

	public static void main(String[] args) throws FindFailed, Exception {
		// TODO Auto-generated method stub
	
		root = System.getProperty("user.dir") + "\\bin\\";

		ExcelManager excelManager=new ExcelManager(new FilloExcelGateway());
		UpdateLogger logger=new UpdateLogger();
		SikuliRobot sikuliRobot=new SikuliRobot();
		RobotFactory robotFactory=new AsycudaRobot(sikuliRobot,logger);
		AutomationManager automationManager=new AutomationManager(robotFactory);
		AsycudaUI asycudaUI = new AsycudaUI(excelManager,automationManager,"Asycuda World Egm Automation 2.4");
		
		

	}

}
