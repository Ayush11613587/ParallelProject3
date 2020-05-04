package com.flightReservation.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
 

@Entity
@Table(name="flight")
public class Flights implements Serializable{

	private static final long serialversionUID=1L;
	@Id
	String flightNumber;
	String source,destination,flightCompany;
	int cost,availableSeats;
	Date journeyDate;
	
	
	@OneToMany(mappedBy="flight")
	List<Tickets> tickets=new LinkedList<>();
	public Flights() {
		// TODO Auto-generated constructor stub
	}
	public Flights(String flightNumber, String source, String destination,String flightCompany, int cost, int availableSeats,Date journeyDate) {
		super();
		this.flightNumber = flightNumber;
		this.source = source;
		this.destination = destination;
		this.flightCompany=flightCompany;
		this.cost = cost;
		this.availableSeats = availableSeats;
		this.journeyDate=journeyDate;
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFlightCompany() {
		return flightCompany;
	}
	public void setFlightCompany(String flightCompany) {
		this.flightCompany = flightCompany;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Date getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}
	@JsonIgnore
	public List<Tickets> getTickets() {
		return tickets;
	}
	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}
	public void addTickets(Tickets ticket) {
		ticket.setFlight(this);
		this.getTickets().add(ticket);
	}
}
