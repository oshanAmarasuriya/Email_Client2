package com.emailClientFiles;

public class RecipientFactory {

    private static RecipientFactory instance=null;

    private RecipientFactory(){}

    public static RecipientFactory getInstance(){
        if(instance==null){
            instance=new RecipientFactory();
        }
        return instance;
    }

    public Recipient getRecipient(String line){
        //factory implementation
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
