package edu.ycp.cs.cs496.locations.model;

public class Cab {
	private String name;
	private String phonenumber;
	private String notes;
	
	public Cab(){
		
	}

	public Cab(String name, String phonenumber, String notes){
		this.name = name;
		this.phonenumber = phonenumber;
		this.notes = notes;
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
}
