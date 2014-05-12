package edu.ycp.cs.cs496.locations.model;

public class Cab {
	private int id;
	private String name;
	private String phonenumber;
	private String notes;
	
	public Cab(){
		
	}

	public Cab(int id, String name, String phonenumber, String notes){
		this.name = name;
		this.phonenumber = phonenumber;
		this.notes = notes;
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
	
	public String getNotes(){
		return notes;
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
	public void setNotes(String notes) {
		this.notes = notes;
		
	}
}
