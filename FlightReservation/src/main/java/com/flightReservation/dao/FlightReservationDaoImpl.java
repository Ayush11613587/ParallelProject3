 package com.flightReservation.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;

@Repository
public class FlightReservationDaoImpl implements IFlightReservationDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean addUser(User newUser) {
		User user=entityManager.find(User.class, newUser.getUserId());
		if(user!=null) {
			return false;
		}
		entityManager.persist(newUser);
		return true;
	}
	@Override
	public User findUserById(String userId) {
		User user=entityManager.find(User.class, userId);
		return user;
	}
	@Override
	public Flights getFlightById(String flightNumber) {
		Flights flight=entityManager.find(Flights.class, flightNumber);
		if(flight==null) {
			return null;
		}
		return flight;
	}
	@Override
	public List<Flights> getAllFlights(String source,String destination,String journeyDate){
		String command="select flight from Flights flight where flight.source=:source and flight.destination=:destination and to_char(flight.journeyDate,'yyyy-mm-dd')=:date";
		TypedQuery<Flights> query=entityManager.createQuery(command,Flights.class);
		query.setParameter("source", source.toLowerCase());
		query.setParameter("destination", destination.toLowerCase());
		query.setParameter("date", journeyDate);
		List<Flights> flights=query.getResultList();
		return flights;
	}
	@Override
	public boolean bookTickets(Tickets tickets[]) {
		Flights flight=tickets[0].getFlight();
		flight.setAvailableSeats(flight.getAvailableSeats()-tickets.length);
		entityManager.merge(flight);
		for(int i=0;i<tickets.length;i++) {
			tickets[i].getUser().addTickets(tickets[i]);
			tickets[i].getFlight().addTickets(tickets[i]);
			entityManager.persist(tickets[i]);
		}
		return true;
	}
	@Override
	public List<Tickets> getBookedTickets(String userId){
		User user=entityManager.find(User.class, userId);
		String command="select ticket from Tickets ticket where ticket.user=:user";
		TypedQuery<Tickets> query=entityManager.createQuery(command,Tickets.class);
		query.setParameter("user",user);
		List<Tickets> tickets=query.getResultList();
		return tickets;
	}
	@Override
	public boolean cancelTicket(int ticketNumber) {
		Tickets ticket=entityManager.find(Tickets.class, ticketNumber);
		ticket.getFlight().setAvailableSeats(ticket.getFlight().getAvailableSeats()+1);
		entityManager.merge(ticket.getFlight());
		entityManager.remove(ticket);
		return true;
	}
}
