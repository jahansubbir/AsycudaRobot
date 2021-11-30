package main;

import main.Robot;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

import javax.swing.JPanel;


import org.python.antlr.PythonParser.else_clause_return;
import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.dal.UpdateLogger;
import com.models.Egm;

public class SikuliRobot extends Robot {

	private Screen screen;

	public SikuliRobot() {

	}

	public void CreateNewManifest(Egm egm) throws FindFailed, Exception {

		screen = new Screen();
		// String match=screen.text();

		screen.wait(FILE.similar(0.9f), 30).click();
		// screen.click(FILE);
		Thread.sleep(1000);
		screen.mouseMove(10, 40);
		/*
		 * screen.wait(DOCUMENTLIBRARY.similar(0.9f), 30).click(); Region
		 * libraryRegion=screen.find(DOCUMENTLIBRARY.similar(0.9f));
		 * screen.click(libraryRegion);
		 */
		Thread.sleep(500);
		screen.wait(CLICKDOCUMENTLIBRARY.similar(0.9f), 30).hover();
		Thread.sleep(500);
		int y = screen.y;

		// screen.mouseMove(100,y);
		// screen.wait(ASYCUDA.similar(0.5f), 30).hover();
		screen.wait(CLICKASYCUDA.similar(0.8f), 30).hover();
		screen.wait(CARGOMANIFEST.similar(0.9f), 30).click();
		System.out.println("hover on cargo manifest");
		// screen.wait(DATAMANAGEMENT.similar(0.9f), 30).click();
		screen.wait(CLICKDATAMANAGEMENT.similar(0.9f), 30).click();
		// screen.wait(MANUALCAPTURE.similar(0.9f), 30).click();
		screen.wait(CLICKMANUALCAPTURE.similar(0.9f), 30).click();
		screen.wait(WAYBILL.similar(0.9f), 30).click();
		screen.wait(CLICKWAYBILL.similar(0.9f), 30).click();
		screen.wait(ADDMANIFEST.similar(0.9f), 30).click();
		screen.wait(IMPORTXML.similar(0.9f), Integer.MAX_VALUE);
		// screen.wait(CLICKADDMANIFEST.similar(0.9f), 30).click();

	}

	public void ImportFromXml(Egm egm, String filePath) throws FindFailed, InterruptedException {
		findXml(filePath);
		for (int i = 0; i < 7; i++) {

			screen.type(Key.TAB);
		}
		screen.type(egm.getMasterBL());
		//findXml(filePath);
	}

	private void findXml(String filePath) throws FindFailed, InterruptedException {
		screen.wait(IMPORTXML.similar(0.9f), 30).click();
		screen.wait(OPENFILEDIALOG.similar(0.9f), 30);
		int x = screen.wait(FILEINPUT.similar(0.9f), 30).x;
		int y = screen.wait(FILEINPUT.similar(0.9f), 30).y;
		screen.mouseMove(x, y + 30);
		screen.click();

		screen.type("a", KeyModifier.CTRL);
		screen.type(Key.BACKSPACE);
		screen.type(filePath);
		screen.wait(OKBUTTON.similar(0.9f), 40).click();

		Thread.sleep(4000);
		screen.mouseMove(30, 100);
		// screen.wait(IMPORTSUCCESS.similar(0.9f), 30).hover();
		// x = screen.wait(IMPORTSUCCESS.similar(0.9f), 30).getX();
		// y = screen.wait(IMPORTSUCCESS.similar(0.9f), 30).getY();
		// screen.mouseMove(x-30,y-100);
		if (screen.exists(CONFIRMIMPORT.similar(0.9f), 30) != null) {
			screen.wait(CONFIRMIMPORT.similar(0.9f), 30).click();
		} else if (screen.exists(CONFIRMIMPORT2.similar(0.9f), 30) != null) {
			screen.wait(CONFIRMIMPORT2.similar(0.9f), 30).click();
		}

		// screen.wait();
	}

	// @SuppressWarnings("finally")
	public boolean FillSadExport(Egm egm) {
		boolean sadAttached = false;
		try {
			System.out.println("Click SAD Export");
			screen.wait(SADEXPORT.similar(0.9f), 30).click();
			System.out.println("declaration Office");
			screen.wait(DECLARATIONOFFICE.similar(0.9f), 30).click();
			screen.type(egm.getOfficeCode());
			Thread.sleep(200);
			screen.type(Key.TAB);
			// screen.wait(timeout);();
			Thread.sleep(200);
			screen.type(Key.TAB);
			Thread.sleep(200);

			screen.type(egm.getEfrNo());
			screen.type(Key.TAB);
			// screen.wait();
			Thread.sleep(200);
			// screen.type(Key.TAB);
			// Thread.sleep(200);

			screen.type(egm.getDate());
			screen.wait(ATTACHBUTTON.similar(0.9f), 30).click();

			if (screen.exists(SADATTACHED.similar(0.9f), 20) != null) {
				System.out.println("attached");
				sadAttached = true;
			} else if (screen.exists(SADINVALID.similar(0.9f), 20) != null) {
				System.out.println("SAD is Invalid");
				egm.setUpdateStatus("Failed");
				egm.setErrorReason("Invalid SAD");

				screen.mouseMove(screen.getX(), screen.getY() + 50);
				screen.wait(CONFIRMIMPORT.similar(0.9f), 30).click();
				// UpdateLogger.LogStatus(Program.excelFilePath,"Sheet1", "Failed", "INVALID
				// SAD", egm.getBlNo());
				;
			} else if (screen.exists(DUPLICATESAD.similar(0.9f), 20) != null) {
				System.out.println("SAD is Already in use");
				egm.setUpdateStatus("Failed");
				egm.setErrorReason("Duplicate SAD");
				// UpdateLogger.LogStatus(Program.excelFilePath,"Sheet1", "Failed", "DUPLICATE
				// SAD", egm.getBlNo());
				screen.mouseMove(screen.getX(), screen.getY() + 50);
				screen.wait(CONFIRMIMPORT.similar(0.9f), 30).click();

			} else {
				egm.setUpdateStatus("Failed");
				egm.setErrorReason("BL not found");
				cancelWaybill();
			}
		} catch (FindFailed findFailed) {
			System.out.println("Find failed");
			if (screen.exists(DUPLICATESAD.similar(0.9f), 20) != null) {
				System.out.println("SAD is Already in use");
				egm.setUpdateStatus("Failed");
				egm.setErrorReason("Duplicate SAD");

			} else if (screen.exists(SADINVALID.similar(0.9f), 30) != null) {
				System.out.println("SAD is Invalid");
				egm.setUpdateStatus("Failed");
				egm.setErrorReason("Invalid SAD");
				UpdateLogger.LogStatus(Program.excelFilePath, "Sheet1", egm);
				// return false;
			}
			try {
				screen.mouseMove(screen.getX(), screen.getY() + 50);
				screen.wait(CONFIRMIMPORT.similar(0.9f), 30).click();
				UpdateLogger.LogStatus(Program.excelFilePath, "Sheet1", egm);
				/// cancelWaybill();
			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (InterruptedException interruptedException) {
			// TODO: handle exception
			// return false;
		} finally {

		}
		UpdateLogger.LogStatus(Program.excelFilePath, "Sheet1", egm);
		return sadAttached;
	}

	public boolean VerifyDocument(Egm egm, int containerCount) throws FindFailed {
		boolean verified = false;
		try {
			screen.wait(BLAWR.similar(0.9f), 50).click();
			Thread.sleep(200);
			/*
			 * screen.wait(SCROLLBAR.similar(0.9f),30).mouseDown(Button.LEFT); int
			 * x=screen.getX(); int y=screen.getY(); screen.mouseMove(x,y+130);
			 * screen.mouseUp();
			 */
			for (int i = 0; i < 9; i++) {
				screen.type(Key.TAB);
				Thread.sleep(300);

			}
			// screen.wait(TOTALCONTAINERS2.similar(0.9f),20).click();
			screen.type(containerCount + "");
			for (int i = 0; i < 2; i++) {
				screen.type(Key.TAB);
				Thread.sleep(300);
			}
			screen.type("a", KeyModifier.CTRL);
			screen.type("c", KeyModifier.CTRL);
			String manifestedCWeight = getClipboardText();
			manifestedCWeight = manifestedCWeight.replace(",", "");
			egm.setManifestedCWeight(Double.parseDouble(manifestedCWeight));
			Thread.sleep(200);
			clearClipboardText();
			screen.type(Key.TAB);
			Thread.sleep(200);
			screen.type("a", KeyModifier.CTRL);
			screen.type("c", KeyModifier.CTRL);
			Thread.sleep(200);
			String manifestedCbm = getClipboardText();
			manifestedCbm = manifestedCbm.replace(",", "");
			egm.setManifestedCbm(Double.parseDouble(manifestedCbm));
			clearClipboardText();
			if (egm.getManifestedCWeight() != egm.getcWeight()) {
				egm.setUpdateStatus("Failed!");
				egm.setErrorReason(
						"Mismatched CWeight-> BE:" + egm.getManifestedCWeight() + "; Excel: " + egm.getcWeight());
			} else {
				screen.wait(VERIFY.similar(0.9f), 30).click();

				Thread.sleep(1000);
				if (screen.exists(ERROR.similar(0.9f), 20) == null) {

					screen.wait(DOCUMENTVERIFIED.similar(0.9f), 20).hover();
					screen.mouseMove(30, 100);
					screen.wait(CONFIRMIMPORT.similar(0.9f), 30).click();
					verified = true;
				} else {
					egm.setUpdateStatus("Failed!");
					egm.setErrorReason("Check if Manifest Exist");
					verified = false;
				}
			}
		} catch (FindFailed findFailed) {
			// TODO: handle exception
			System.out.println(findFailed.getMessage());
			if (screen.exists(ERROR.similar(0.9f), 20) != null) {
				egm.setUpdateStatus("Failed!");
				egm.setErrorReason("Check if Manifest Exist");
			}
			verified = false;
			System.out.println(findFailed.getMessage());
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			screen.findText("Manifest");
		}
		// screen.findText("Manifest");
		UpdateLogger.LogStatus(Program.excelFilePath, "Sheet1", egm);
		return verified;
	}

	public void Submit(Egm egm) throws FindFailed {
		screen.wait(SAVE.similar(0.9f), 30).click();
		screen.wait(SUBMITOK.similar(0.9f), 50).click();
		egm.setUpdateStatus("Success!");
		UpdateLogger.LogStatus(Program.excelFilePath, "Sheet1", egm);
	}

	private boolean Exists(Pattern pattern) {
		if (screen.exists(pattern.similar(0.9f), 30) != null) {
			return true;
		} else
			return false;
	}

	public void checkError() throws FindFailed {
		screen.wait(ERROR.similar(0.9f), 50).click();
	}

	public boolean cancelWaybill() {
		try {
			screen.wait(WAYBILLFILE.similar(0.9f), 20).click();
			screen.wait(CANCELWAYBILL.similar(0.9f), 30).click();
			screen.wait(ASKCONFIRMCANCEL.similar(0.9f), 30).mouseMove();
			screen.mouseMove(screen.getX(), screen.getY() + 30);
			screen.wait(CONFIRMIMPORT.similar(0.9f), 30).click();

			return true;
		} catch (FindFailed findFailed) {
			// TODO: handle exception
			return false;
		}

	}

	private String getClipboardText() {
		String output = "";

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			output = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // (stringSelection, null);
		return output;
	}

	private void clearClipboardText() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(new Transferable() {

			@Override
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public DataFlavor[] getTransferDataFlavors() {
				// TODO Auto-generated method stub
				return new DataFlavor[0];
			}

			@Override
			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
				// TODO Auto-generated method stub
				throw new UnsupportedFlavorException(flavor);
			}
		}, null);
	}

}
