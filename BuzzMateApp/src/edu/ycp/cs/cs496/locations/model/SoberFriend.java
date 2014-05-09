package edu.ycp.cs.cs496.locations.model;

public class SoberFriend {
	private String name;
	private String phonenumber;
	private char isUser;
	private char isAvail;
	private int userID;
	
	public SoberFriend(){
		
	}
	
	public SoberFriend(String name, String phonenumber, char isUser, int userID, char isAvailable) {
		this.name = name;
		this.phonenumber = phonenumber;
		this.isUser = isUser;
		this.isAvail = isAvailable;
		this.userID = userID; 
	}

	public String getName() {
		return name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public char getIsUser() {
		return isUser;
	}

	public char getIsAvail() {
		return isAvail;
	}
	
	public int getUserID() {
		return userID;
	}

}