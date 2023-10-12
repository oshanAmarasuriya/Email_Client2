package com.emailClientFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializer {
	/* In the class serializing and deserializing of emailhandler objects are done */

	public static void serialize(EmailHandler eh) throws IOException {

		//fileoutputstream to sentmail.ser file in append mode
		FileOutputStream fs = new FileOutputStream("sentmail.ser", true);

		if (new File("sentmail.ser").length() == 0) {
			//in case of new file
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(eh); //saving email handler object to the file
			os.close();
		} else {
			CustomObjectOutputStream cs = new CustomObjectOutputStream(fs);
			cs.writeObject(eh);
			cs.close();
		}
	}
	
	
	public static ArrayList<EmailHandler> getSentEmails(String date) throws IOException{
		//retrive sent mail objects from the serialization file by referring of a date
			ArrayList<EmailHandler> arr=new ArrayList<EmailHandler>();
			
			 try
			 {
				 FileInputStream fs= new FileInputStream("sentmail.ser");
				 ObjectInputStream os=new ObjectInputStream(fs);
			   while (true){
			     Object obj = os.readObject();
			     if(((EmailHandler)obj).getSent_date().equals(date)) {
			    	 arr.add((EmailHandler)obj);
			     }
			   }
//			   os.close();
			 }
			 catch (Exception e)
			 {
			   
			 }

			 return arr;
		}
	
	
}
