package com.flightReservation.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="user_flight")
public class User implements Serializable{

	private static final long serialversionUID=1L; 
	@Id
	private String userId;
	private String firstName,lastName,password;
	private String gender;
	private long mobileNumber;
	
	
	@OneToMany(mappedBy="user")
	private List<Tickets> bookedTickets=new LinkedList<>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String firstName, String lastName, String password, String gender, long mobileNumber) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void addTickets(Tickets ticket) {
		ticket.setUser(this);
		this.getBookedTickets().add(ticket);
	}
	
	@JsonIgnore
	public List<Tickets> getBookedTickets() {
		return bookedTickets;
	}

	public void setBookedTickets(List<Tickets> bookedTickets) {
		this.bookedTickets = bookedTickets;
	}
	
}
