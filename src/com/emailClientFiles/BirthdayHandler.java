package com.emailClientFiles;


import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class BirthdayHandler {
	/* Birthday related operations */
	private static Date date = new Date();
	private static LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	private static final int current_month = localDate.getMonthValue();
	private static final int today = localDate.getDayOfMonth();
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static FileHandler filehandler=new FileHandler();
	
	public static void wishForBirthday() throws Exception {
		//sending birthday wishes and serialization.
		ArrayList<Recipient> birthdayPeopleOnCurrentDay = new ArrayList<Recipient>();
		//fetch bairthday people data
		birthdayPeopleOnCurrentDay = findBirthdayCelebs( current_month, today);
		//getting userinfo who has been sent a wish already
		ArrayList<EmailHandler> already_sentmail = Serializer.getSentEmails(dateFormat.format(date));
		
		FileOutputStream fs = new FileOutputStream("sentmail.ser", true);
		
		int flag = 0;
		if (already_sentmail.size() == 0) {//no one has sent a wish
			
			for (int i = 0; i < birthdayPeopleOnCurrentDay.size(); i++) {
				sendWish(birthdayPeopleOnCurrentDay.get(i), fs);
			}
			System.out.println("\nEmails are being sent to people who celebrete birthday today!");

		}
		else {
			//sorting already wished people and sending to newly added people.
			boolean alreadySent = false;
			
			for (int i = 0; i < birthdayPeopleOnCurrentDay.size(); i++) {
				for (int j = 0; j < already_sentmail.size(); j++) {
					
					if ((birthdayPeopleOnCurrentDay.get(i).getEmail()).equals(already_sentmail.get(j).getAddress())) {
						alreadySent = true;
						break;
					
					}
				}
				if (!alreadySent) {
					sendWish(birthdayPeopleOnCurrentDay.get(i), fs);
					flag += 1;
				}
				alreadySent = false;
			}

			if (flag > 1) {
				System.out.println("\nEmails are being sent to people who celebrete birthday today!");
			} else if (flag == 1) {
				System.out.println("\nA greeting email is sent for new client!");
			} else {
				System.out.println("\nEmails for birthday wishes are already sent for today!");
			}

		}
	}

	private static void sendWish(Recipient current, FileOutputStream fs) throws Exception {
		//generate wish according to user type and send
		String wish = "";
		if (current instanceof OfficialFriendRecipient) {
			wish = ((OfficialFriendRecipient)current).wish(); // downcating

		} else {
			wish =((PersonalRecipient)current).wish();
		}
		String subject="Dear "+current.getName()+",";
		EmailHandler eh=new EmailHandler(current.getName(),subject,wish,current.getEmail(),dateFormat.format(date));
		eh.sendMail();//send
		Serializer.serialize(eh); // serialize the sent mail

	}

	public static ArrayList<Recipient> findBirthdayCelebs(int interested_month, int interested_day) {
		//Get all the birthday people
		ArrayList<Recipient> all_recipients_objects=filehandler.readFromFile(); //polymoprism
		ArrayList<Recipient> bday_recipients = new ArrayList<Recipient>();
		String bdaystr;
		for (int i = 0; i < all_recipients_objects.size(); i++) {
			Recipient res = all_recipients_objects.get(i);
			if (!(res instanceof OfficialRecipient)) {
				// makes the list of birthday people on current day

				if (res instanceof OfficialFriendRecipient) {		//downcasting with precausions
					bdaystr = ((OfficialFriendRecipient) res).getBirthday();
					if (isBirthday(bdaystr, interested_month, interested_day)) {
						bday_recipients.add(res);
					}

				} else {
					bdaystr = ((PersonalRecipient) res).getBirthday();
					if (isBirthday(bdaystr, interested_month, interested_day)) {
						bday_recipients.add(res);
					 }
				}

			}
		}
		return bday_recipients;
	}

	private static boolean isBirthday(String bdaystr, int interested_month, int interested_day) {
		//check given day is matching with birthday of a particular user.
		String[] split_result = bdaystr.split("/");
		
		if ((Integer.parseInt(split_result[1]) == interested_month) && (Integer.parseInt(split_result[2]) == interested_day)) {
			return true;
		}
		return false;
		
	}


}
