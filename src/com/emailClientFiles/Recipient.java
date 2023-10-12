package com.emailClientFiles;

public  class Recipient { // base class for all the recipient types
	private String name,email;
	
	protected Recipient(String name, String email) {
		this.name=name;
		this.email=email;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
