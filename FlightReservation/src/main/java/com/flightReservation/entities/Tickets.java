package com.flightReservation.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="ticket")
public class Tickets implements Serializable{

	private static final long serialversionUID=1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int ticketNumber;
	
	String passengerName;
	int passengerAge;
	String passengerGender;
	
	
	@ManyToOne
	@JoinColumn(name="userId",nullable=false)
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name="flightNumber",nullable=false)
	private Flights flight;
public Tickets() {
	// TODO Auto-generated constructor stub
}
	public Tickets(String passengerName, int passengerAge, String passengerGender, User user, Flights flight) {
		super();
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerGender = passengerGender;
		this.user = user;
		this.flight = flight;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	public String getPassengerGender() {
		return passengerGender;
	}

	public void setPassengerGender(String passengerGender) {
		this.passengerGender = passengerGender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flights getFlight() {
		return flight;
	}

	public void setFlight(Flights flight) {
		this.flight = flight;
	}
	
	
}
