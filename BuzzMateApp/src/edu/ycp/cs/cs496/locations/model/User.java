package edu.ycp.cs.cs496.locations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	//user fields that will be checked against the database to determine if the credentials are valid
	private int id;
	private String name = "";
	private String password = "";
	private String phonenumber = "";
	private String drink = "";
	private String locationName = "";

	//this fields can be taken from the html web page and will instantiate the user object
	public User(int id, String username, String password, String phonenumber, String drink, String locationName){
		this.name = username;
		this.password = password;
		this.id = id;
		this.phonenumber = phonenumber;
		this.drink = drink;
		this.locationName = locationName;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}

	public void setUsername(String un) {
		this.name = un;
	}

	public void setPassword(String pw) {
		this.password = pw;
		
	}
	public String toString() {
		return name; 
	}

	public String getPhonenumber() {
		return phonenumber;
	}
	
	public String getDrink() {
		return drink;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setPhone(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public void setDrink(String drink) {
		this.drink = drink;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}	
}
