package com.emailClientFiles;

/* since only office friends and personal friends are wishable, implementing wishable
interface,having wish() method
 */
public class OfficialFriendRecipient extends Recipient implements wishable{
	private String designation,birthday; //unique properties for this type
	public OfficialFriendRecipient(String name,String mail,String designation,String bday) {
		super(name,mail); //initialize superclass constructor for common attributes
		this.designation=designation;
		this.birthday=bday;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	//concrete implementation of wish()
	@Override
	public String wish() {
		return "Wish you a Happy Birthday. " + sender_name;
		
	}
	
}
