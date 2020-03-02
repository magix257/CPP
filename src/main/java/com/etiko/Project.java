package com.etiko;

public class Project {
	
	String clientNumber;
	String labelNumber;
	
	public Project() {}
	
	public Project(String clientNumber, String labelNumber) {
		super();
		this.clientNumber = clientNumber;
		this.labelNumber = labelNumber;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public String getLabelNumber() {
		return labelNumber;
	}
	public void setLabelNumber(String labelNumber) {
		this.labelNumber = labelNumber;
	}
	@Override
	public String toString() {
		return "Project [clientNumber=" + clientNumber + ", labelNumber=" + labelNumber + "]";
	}
	
	

}
