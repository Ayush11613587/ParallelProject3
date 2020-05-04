package com.flightReservation.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightReservation.Exception.FlightNotFound;
import com.flightReservation.Exception.InvalidPasswordException;
import com.flightReservation.Exception.NoTicketFound;
import com.flightReservation.Exception.UserAlreadyExist;
import com.flightReservation.Exception.UserDoesNotExist;
import com.flightReservation.dao.FlightReservationDaoImpl;
import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;

@Service
@Transactional
public class FlightReservationServiceImpl implements IFlightReservationService{
 
	@Autowired
	private FlightReservationDaoImpl flightReservationDaoImpl;
	
	/* This method is used to add new user by using the persist method in dao layer
	 * if in case user is already exist in the database then this function will throw an
	 * error that user is already exist for successfully register user need to give unique userId 
	 * */
	@Override
	public boolean addUser(User newUser) {
		boolean flag= flightReservationDaoImpl.addUser(newUser);
		if(flag==false) {
			throw new UserAlreadyExist("User Id already Exist");
		}
		return flag;
	}
	/*This method is authenticating the user at the time of login 
	 * firstly it will check if user Exist or not for this it calls findUserById(userId)
	 * method after checking this if in case user does not exist then it will throw error user Id does not exist
	 * if in case user exist then it will check for password if password does not match then it will throw error 
	 * that invalid password otherwise it will return user object. 
	 * */
	@Override
	public User authenticateUser(String userId,String password) {
		User user=flightReservationDaoImpl.findUserById(userId);
		if(!user.getPassword().equals(password)) {
			throw new InvalidPasswordException("Invalid Password...");
		}
		return user;
	}
	/*This method is checking whether the user exist or not if user does not exist then it will throw error 
	 * User Id does not exist otherwise it will return the user object
	 * */
	@Override
	public User findUserById(String userId) {
		User user= flightReservationDaoImpl.findUserById(userId);
		if(user==null) {
			throw new UserDoesNotExist("User Id does not Exist");
		}
		return user;
	}
	/*This method is finding flight by flight Number
	 * */
	@Override
	public Flights getFlightById(String flightNumber) {
		return flightReservationDaoImpl.getFlightById(flightNumber);
	}
	/*This method is for getting flight based on source destination and journey date
	 * if in case no such flight exist then it will throw error that "no flight available"
	 * otherwise it will return list of flights having same source destination and journey date
	 * */
	@Override
	public List<Flights> getAllFlights(String source,String destination,String journeyDate){
		List<Flights> flights=flightReservationDaoImpl.getAllFlights(source,destination,journeyDate);
		if(flights.size()==0) {
			throw new FlightNotFound("No Flight Available");
		}
		return flights;
	}
	/*This method is used to checking whether user Id exist or not if in case user Id does not exist
	 * then it will throw error that "user Id does not exist"
	 * otherwise it will boolean variable
	 * */
	@Override
	public boolean isUserIdExist(String userId) {
		User user=flightReservationDaoImpl.findUserById(userId);
		if(user==null) {
			throw new UserDoesNotExist("User Id does not Exist");
		}
		return true;
	}
	/*This method is for booking tickets has parameter an array of tickets entity class
	 * */
	@Override
	public boolean bookTickets(Tickets tickets[]) {
		
		return flightReservationDaoImpl.bookTickets(tickets);
	}
	/*This method is for getting all the user's booked tickets if in case user does not have any booked ticket
	 * then it will throw error that "you don't have any booked ticket"
	 * otherwise it will return list of all user's booked tickets 
	 * */
	@Override
	public List<Tickets> getBookedTickets(String userId){
		List<Tickets> bookedTickets=flightReservationDaoImpl.getBookedTickets(userId);
		if(bookedTickets.size()==0) {
			throw new NoTicketFound("You Havn't booked any tickets");
		}
		return bookedTickets;
	}
	/*This method is for cancel the booked ticket taking ticketNumber as a parameter
	 * */
	@Override
	public boolean cancelTicket(int ticketNumber) {
		return flightReservationDaoImpl.cancelTicket(ticketNumber);
	}
}
