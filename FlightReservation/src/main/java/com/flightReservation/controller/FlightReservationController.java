package com.flightReservation.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;
import com.flightReservation.service.FlightReservationServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FlightReservationController {
	
	@Autowired
	private FlightReservationServiceImpl flightReservationServiceImpl;
	
	@PostMapping("/addNewUser")
	public boolean addNewUser(@RequestBody User newUser){
		boolean flag=flightReservationServiceImpl.addUser(newUser);
		return flag;
	}
	@GetMapping("/authenticateUser/{userId}/{password}")
	public User authenticateUser(@PathVariable("userId") String userId,@PathVariable("password") String password) {
		return flightReservationServiceImpl.authenticateUser(userId, password);
	}
	@GetMapping("/isUserIdExist/{userId}")
	public boolean isUserIdExist(@PathVariable("userId") String userId) {
		boolean flag=flightReservationServiceImpl.isUserIdExist(userId);
		return flag;
	}
	@GetMapping("/flights/{flightNumber}")
	public ResponseEntity<Flights> getFlightById(@PathVariable("flightNumber") String flightNumber){
		Flights flight=flightReservationServiceImpl.getFlightById(flightNumber);
		return new ResponseEntity<Flights>(flight,HttpStatus.OK);
	}
	
	@GetMapping("/flights/{source}/{destination}/{journeyDate}")
	public ResponseEntity<List<Flights>> getAllFlights(@PathVariable("source") String source,@PathVariable("destination") String destination,@PathVariable("journeyDate") String journeyDate){
		
			List<Flights> flights=flightReservationServiceImpl.getAllFlights(source,destination,journeyDate);
			return new ResponseEntity<List<Flights>>(flights,HttpStatus.OK);
	}
	@PostMapping("/bookTickets")
	public boolean addNewUser(@RequestBody Tickets tickets[]){
		boolean flag=flightReservationServiceImpl.bookTickets(tickets);
		return flag;
	}
	@GetMapping("/getBookedTickets/{userId}")
	public ResponseEntity<List<Tickets>> getBookedTickets(@PathVariable("userId") String userId){
		List<Tickets> tickets=flightReservationServiceImpl.getBookedTickets(userId);
		return new ResponseEntity<List<Tickets>>(tickets,HttpStatus.OK);
	}
	@DeleteMapping("/cancelTicket/{ticketNumber}")
	public boolean cancelTicket(@PathVariable("ticketNumber") int ticketNumber) {
		return flightReservationServiceImpl.cancelTicket(ticketNumber);
	}
	}
