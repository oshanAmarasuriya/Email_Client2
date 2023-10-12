package com.emailClientFiles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Email_Client {

	private static Date date = new Date();
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");


	public static void main(String[] args) throws Exception {
		FileHandler filehandler = FileHandler.getInstance();
		BirthdayHandler.wishForBirthday();
		Scanner scanner = new Scanner(System.in);
        
		Scanner scanner1 = new Scanner(System.in).useDelimiter("\n");
        boolean ctrl = true;
        while (ctrl) {
		
		System.out.println("Enter option type: \n" + "1 - Adding a new recipient\n" + "2 - Sending an email\n"
				+ "3 - Printing out all the recipients who have birthdays\n"
				+ "4 - Printing out details of all the emails sent\n"
				+ "5 - Printing out the number of recipient objects in the application");
		
		int option = scanner.nextInt();
		
		switch (option) {
		case 1:
			// input format - Official: nimal,nimal@gmail.com,ceo
			Scanner sc1 = new Scanner(System.in);

			System.out.println("Enter recipient datails:");
			String inputString = sc1.nextLine();
			filehandler.writeToFile(inputString);
			BirthdayHandler.wishForBirthday();

			break;
			
		case 2:
			// input format - email, subject, content
			// code to send an email
			//Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter email info (email, subject, content):");
			String emailDetails = scanner1.next();
			

			
			EmailHandler eh=new EmailHandler("<not specified>", emailDetails.split(", ")[1], emailDetails.split(", ")[2],emailDetails.split(", ")[0], dateFormat.format(date));
			eh.sendMail();
			Serializer.serialize(eh);
			 
			
			//sc2.close();
			break;
			
		case 3:
			// input format - yyyy/MM/dd (ex: 2018/09/17)
			// code to print recipients who have birthdays on the given date
			System.out.println("Enter date");
			
			String inputDate = scanner1.nextLine();
			System.out.println("People having birthday on " + inputDate + " are : ");
			
			ArrayList<Recipient> birthdayPeopleOnCustomDay = new ArrayList<Recipient>();
			birthdayPeopleOnCustomDay = BirthdayHandler.findBirthdayCelebs(Integer.parseInt(inputDate.split("/")[1]), Integer.parseInt(inputDate.split("/")[2]));
			
			for (int i = 0; i < birthdayPeopleOnCustomDay.size(); i++) {
				System.out.println(birthdayPeopleOnCustomDay.get(i).getName()+ ", " + birthdayPeopleOnCustomDay.get(i).getEmail());
			}
			

			break;
			
		case 4:
			// input format - yyyy/MM/dd (ex: 2018/09/17)
			// code to print the details of all the emails sent on the input date
			System.out.println("Enter date");
			
			String inputSentDate = scanner1.nextLine();
			System.out.println("All messeges, sent on " + inputSentDate + " are : ");

			//Deserialize and fetch sent mail objects
			ArrayList<EmailHandler> filtered_sentmail = Serializer.getSentEmails(inputSentDate);
			//System.out.println(filtered_sentmail.get(0));
			
			for (int i = 0; i < filtered_sentmail.size(); i++) {
				System.out.println("Sent to: " + filtered_sentmail.get(i).getName() + ", email: "
						+ filtered_sentmail.get(i).getAddress() + ", Subject: " + filtered_sentmail.get(i).getSubject()
						+ ", Content: " + filtered_sentmail.get(i).getContent());
			}

			//sc4.close();
			break;
			
		case 5:
			// code to print the number of recipient objects in the application

			System.out.println("Number of all recipients: ");
			System.out.println(FileHandler.getRecipient_count());

			break;
			
		
		}
		
		}//loop
	}
	

	}
