package main;

import org.sikuli.script.Pattern;

public class Robot {
	private String root = System.getProperty("user.dir") + "\\bin\\";
	public Robot() {
		// TODO Auto-generated constructor stub
		//this.getClass().getResourceAsStream();
	}
	protected final Pattern FILE = new Pattern(this.getClass().getResource("images/file.png"));//(this.getClass().getResource("images/file.png"));
	protected final Pattern DOCUMENTLIBRARY = new Pattern(this.getClass().getResource("images/DocumentLibrary.png"));//new Pattern((this.getClass().getResource("images/DocumentLibrary.png"));
	protected final Pattern CLICKDOCUMENTLIBRARY = new Pattern(this.getClass().getResource("images/clickDocumentLibrary.png"));
	protected final Pattern ASYCUDA = new Pattern(this.getClass().getResource("images/asycuda.png"));
	protected final Pattern CLICKASYCUDA = new Pattern(this.getClass().getResource("images/clickAsycuda.png"));
	protected final Pattern CARGOMANIFEST = new Pattern(this.getClass().getResource("images/cargoManifest.png"));
	protected final Pattern CLICKDATAMANAGEMENT = new Pattern(this.getClass().getResource("images/clickDataManagement.png"));
	protected final Pattern MANUALCAPTURE = new Pattern(this.getClass().getResource("images/manualCapture.png"));
	protected final Pattern CLICKMANUALCAPTURE = new Pattern(this.getClass().getResource("images/clickManualCapture.png"));
	protected final Pattern WAYBILL = new Pattern(this.getClass().getResource("images/waybill.png"));
	protected final Pattern CLICKWAYBILL = new Pattern(this.getClass().getResource("images/clickWayBill.png"));
	protected final Pattern ADDMANIFEST = new Pattern(this.getClass().getResource("images/addManifest.png"));
	protected final Pattern CLICKADDMANIFEST = new Pattern(this.getClass().getResource("images/clickAddManifest.png"));

	protected final Pattern IMPORTXML = new Pattern(this.getClass().getResource("images/importFromXml.png"));
	protected final Pattern OPENFILEDIALOG = new Pattern(this.getClass().getResource("images/openFileDialog.png"));
	protected final Pattern FILEINPUT = new Pattern(this.getClass().getResource("images/fileInput.png"));
	protected final Pattern OKBUTTON = new Pattern(this.getClass().getResource("images/okButton.png"));
	protected final Pattern IMPORTSUCCESS = new Pattern(this.getClass().getResource("images/importSuccess.png"));
	protected final Pattern CONFIRMIMPORT = new Pattern(this.getClass().getResource("images/confirmImport.png"));
	protected final Pattern CONFIRMIMPORT2 = new Pattern(this.getClass().getResource("images/confirmImport2.png"));
	protected final Pattern SADEXPORT = new Pattern(this.getClass().getResource("images/sadExport.png"));
	protected final Pattern DECLARATIONOFFICE=new Pattern(this.getClass().getResource("images/office.png"));
	protected final Pattern EFRNO=new Pattern(this.getClass().getResource("images/efrNo.png"));
	protected final Pattern EFRDATE=new Pattern(this.getClass().getResource("images/efrDate.png"));
	protected final Pattern ATTACHBUTTON=new Pattern(this.getClass().getResource("images/attachButton.png"));
	protected final Pattern SADATTACHED=new Pattern(this.getClass().getResource("images/sadAttached.png"));
	
	protected final Pattern BLAWR= new Pattern(this.getClass().getResource("images/blTrAwr.png"));
	protected final Pattern VERIFY=new Pattern(this.getClass().getResource("images/verify.png"));
	protected final Pattern TOTALCONTAINERS=new Pattern(this.getClass().getResource("images/totalContainers.png"));
	protected final Pattern TOTALCONTAINERS2=new Pattern(this.getClass().getResource("images/totalContainers2.png"));
	protected final Pattern SCROLLBAR=new Pattern(this.getClass().getResource("images/scrollbar.png"));
	protected final Pattern DOCUMENTVERIFIED=new Pattern(this.getClass().getResource("images/documentVerified.png"));
	
	protected final Pattern SAVE=new Pattern(this.getClass().getResource("images/save.png"));
	protected final Pattern SUBMITOK=new Pattern(this.getClass().getResource("images/submitOk.png"));
	
	protected final Pattern ERROR=new Pattern(this.getClass().getResource("images/error.png"));
	protected final Pattern SADINVALID=new Pattern(this.getClass().getResource("images/invalidSADError.png"));
	protected final Pattern DUPLICATESAD=new Pattern(this.getClass().getResource("images/sadDuplicateError.png"));
	protected final Pattern WAYBILLFILE=new Pattern(this.getClass().getResource("images/waybillFile.png"));
	protected final Pattern CANCELWAYBILL=new Pattern(this.getClass().getResource("images/cancelWaybill.png"));
	protected final Pattern ASKCONFIRMCANCEL=new Pattern(this.getClass().getResource("images/askConfirmCancel.png"));
	
}
