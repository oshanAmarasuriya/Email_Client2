package com.emailClientFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/* ===============================================
		Factory Method (Polymoprism)
		Used Singleton to make a single point of access to this class
*/

public class FileHandler {
	/* txt file related operations */
	private static int recipient_count;
	private static FileHandler instance;

	private FileHandler(){}

	public static FileHandler getInstance() {
		if (instance == null) {
			instance = new FileHandler();
		}
		return instance;
	}

	public static int getRecipient_count() {
		return recipient_count;
	}

	public void writeToFile(String inputdata) {
		//write input client info to the file
		String newLine = System.getProperty("line.separator");
		String writestr=inputdata+newLine;
		
		try {
			//file handling
			FileWriter fw= new FileWriter("clientList.txt",true);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(writestr);
			bw.close();
			System.out.println("Successfully added!");
			
		}catch(IOException ex) {
			ex.printStackTrace();
			
		}
		
	}
	
	public ArrayList<Recipient> readFromFile() {
		//Reading from the file
		recipient_count=0;
		ArrayList<Recipient> list=new ArrayList<Recipient>();
		try {
			FileReader fr= new FileReader("clientList.txt");
			BufferedReader bfr=new BufferedReader(fr);
			String line=null;
			while((line=bfr.readLine()) != null) {
				list.add(split_factory(line)); // use a factory to generate various objects.
				recipient_count+=1;
			}
			bfr.close();
		}catch(IOException ex) {
			ex.printStackTrace();
			
		}
		return list;
	}

	private Recipient split_factory(String line) {
		//factory method implementation
		//According to fileinfo, create and return different user objects.
		String[] arr=line.split(",");
		String[] temparr=arr[0].split(" ");
		String type= temparr[0];
		String name= temparr[1];
		if(type.equals("Official:")) {
			return new OfficialRecipient(name,arr[1],arr[2]);
		}else if(type.equals("Office_friend:")) {
			return new OfficialFriendRecipient(name,arr[1],arr[2],arr[3]);
		}else {
			return new PersonalRecipient(name,arr[1],arr[2],arr[3]);
		}
	}
	

	

}
