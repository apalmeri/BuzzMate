package edu.ycp.cs.cs496.locations.model;

public class SoberFriend {
	private int id;
	private String name;
	private String phonenumber;
	
	public SoberFriend(){
		
	}

	public SoberFriend(int id, String name, String phonenumber){
		this.name = name;
		this.phonenumber = phonenumber;
	}

	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPhonenumber(){
		return phonenumber;
	}

	public void setId(int id) {
		this.id = id;
		
	}
	public void setName(String name) {
		this.name = name;
		
	}
	public void setPhone(String phone) {
		this.phonenumber = phone;
		
	}
	
	public String toString() {
		return name; 
	}
}
