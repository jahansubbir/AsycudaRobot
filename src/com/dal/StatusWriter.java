package com.dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.models.Egm;

public class StatusWriter {

	public void WriteStatus(Egm egm) {
		String desktopPath = System.getProperty("user.home") + "/Desktop";
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
		String dateString = dateFormat.format(today);

		String fileName = desktopPath + "/Result_" + dateString + ".txt";
		try {
			File yourFile = new File(fileName);
			yourFile.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(fileName, true);
			String message = egm.getBlNo() + "\t" + egm.getUpdateStatus() + "\t" + egm.getErrorReason() + "\n";
			byte[] bs = message.getBytes();
			outputStream.write(bs);
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
