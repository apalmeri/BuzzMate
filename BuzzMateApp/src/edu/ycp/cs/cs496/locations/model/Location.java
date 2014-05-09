package edu.ycp.cs.cs496.locations.model;

public class Location {
	private String name;
	private String type;
	private String street1;
	private String city;
	private String state;
	private String mailcode;
	private String phonenumber;
	private int id;
	
	
	//Default constructor
	public Location(){
		
	}
	
	//Constructor
	public Location(String name, String type, String street1, String city, String state, String mailcode, String phonenumber){
		this.name = name;
		this.type = type;
		this.street1 = street1;
		this.city = city;
		this.state = state;
		this.mailcode = mailcode;
		this.phonenumber = phonenumber;
	}
	
	//NO SETS?
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public String getStreet1(){
		return street1;
	}
	
	public String getState(){
		return state;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getMailcode(){
		return mailcode;
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

	public Integer getId() {
		return id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setMailcode(String mailcode) {
		this.mailcode = mailcode;	
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}