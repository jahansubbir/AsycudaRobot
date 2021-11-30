package com.models;

public class Egm {
	private int serialNo;
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	private String blNo;
	private String masterBL;
	public void setMasterBL(String masterBL) {
		this.masterBL = masterBL;
	}

	private String officeCode;
	private String efrNo;
	private String date;
	private int totalContainers;
	private double cbm;
	private double manifestedCbm;
	private String UpdateStatus;
	private String ErrorReason;

	public String getUpdateStatus() {
		return UpdateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		UpdateStatus = updateStatus;
	}

	public String getErrorReason() {
		return ErrorReason;
	}

	public void setErrorReason(String errorReason) {
		ErrorReason = errorReason;
	}

	private double manifestedCWeight;

	public double getManifestedCbm() {
		return manifestedCbm;
	}

	public void setManifestedCbm(double manifestedCbm) {
		this.manifestedCbm = manifestedCbm;
	}

	public double getManifestedCWeight() {
		return manifestedCWeight;
	}

	public void setManifestedCWeight(double manifestedCWeight) {
		this.manifestedCWeight = manifestedCWeight;
	}

	private double cWeight;

	public double getCbm() {
		return cbm;
	}

	public void setCbm(double cbm) {
		this.cbm = cbm;
	}

	public double getcWeight() {
		return cWeight;
	}

	public void setcWeight(double cWeight) {
		this.cWeight = cWeight;
	}

	public int getTotalContainers() {
		return totalContainers;
	}

	public void setTotalContainers(int totalContainers) {
		this.totalContainers = totalContainers;
	}

	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getEfrNo() {
		return efrNo;
	}

	public void setEfrNo(String efrNo) {
		this.efrNo = efrNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMasterBL() {
		// TODO Auto-generated method stub
		return this.masterBL;
	}

}
