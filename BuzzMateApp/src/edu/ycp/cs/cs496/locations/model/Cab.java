package edu.ycp.cs.cs496.locations.model;

public class Cab {
	private String name;
	private String phonenumber;
	private String notes;
	private int id;
	
	
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
