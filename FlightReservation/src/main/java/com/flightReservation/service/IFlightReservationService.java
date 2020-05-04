package com.flightReservation.service;

import java.util.List;

import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;

public interface IFlightReservationService {

	public boolean addUser(User newUser);
	public User authenticateUser(String userId,String password);
	public User findUserById(String userId);
	public Flights getFlightById(String flightNumber);
	public List<Flights> getAllFlights(String source,String destination,String journeyDate);
	public boolean isUserIdExist(String userId);
	public boolean bookTickets(Tickets tickets[]);
	public List<Tickets> getBookedTickets(String userId);
	public boolean cancelTicket(int ticketNumber);
}
