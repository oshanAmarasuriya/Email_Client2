package com.emailClientFiles;

public class OfficialRecipient extends Recipient {
	//attributes of an official client
	private String designation; //unique
	public OfficialRecipient(String name,String mail,String designation) {
		super(name,mail); //initialize superclass constructor for common attributes
		this.designation=designation;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}
