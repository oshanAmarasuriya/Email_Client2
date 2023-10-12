package com.emailClientFiles;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

/* ===============================
		Using singleton design pattern to make email handling resources unique throughout the system
 */

public class EmailHandler  implements Serializable { // made the class serializable by implementing this.
	
	private String name,subject,content,address,sent_date;
	private static EmailHandler instance;
	
	private EmailHandler(String name, String subject,String content,String address,String date) {
		this.name=name;
		this.subject=subject;
		this.content=content;
		this.address=address;
		this.sent_date=date;
	}

	public static EmailHandler getInstance(String name, String subject,String content,String address,String date){
		if(instance==null){
			instance=new EmailHandler(name,subject,content,address,date);
		}
		return instance;
	}


	public  void sendMail() throws Exception {
		//email sending operations
		System.out.println("Please wait, sending emails in progress!");
		Properties prop=new Properties();
		
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		// Give email account credentials here
		String myemail="<add the email address>";
		String password="<Add the password>";



		Session sess =Session.getInstance(prop,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myemail, password);
			}
		});
		
		
		Message msg= prepareMessage(sess,myemail);		
		try {
			Transport.send(msg);
			System.out.println("Email sent successfully!");
		}catch(Exception e){
			System.out.println("Email Account Access Not Available!");
		}
	}

	private  Message prepareMessage(Session s,String myemail) {
		
		try {
			Message msg= new MimeMessage(s);
			msg.setFrom(new InternetAddress(myemail));
			msg.setRecipient(Message.RecipientType.TO,new InternetAddress(address));
			msg.setSubject(subject);
			msg.setText(content);
			return msg;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public String getName() {
		return name;
	}
	public String getSubject() {
		return subject;
	}
	public String getContent() {
		return content;
	}
	public String getAddress() {
		return address;
	}
	public String getSent_date() {
		return sent_date;
	}
	
	//Is this out of git?? locked??
	
}