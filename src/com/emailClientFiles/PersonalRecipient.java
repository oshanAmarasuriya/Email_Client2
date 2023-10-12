package com.emailClientFiles;

/* since only office friends and personal friends are wishable, implementing wishable
interface,having wish() method
 */
public class PersonalRecipient extends Recipient implements wishable{
	private String nickname,birthday; //unique
	public PersonalRecipient(String name,String nickname,String mail,String bday) {
		super(name,mail); //initialize superclass constructor for common attributes
		this.nickname=nickname;
		this.birthday=bday;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
		return "Hugs and love on your birthday. " + sender_name;
	}
}
