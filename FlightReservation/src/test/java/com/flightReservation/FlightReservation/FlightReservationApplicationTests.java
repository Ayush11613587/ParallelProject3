package com.flightReservation.FlightReservation;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.flightReservation.Exception.FlightNotFound;
import com.flightReservation.Exception.InvalidPasswordException;
import com.flightReservation.Exception.NoTicketFound;
import com.flightReservation.Exception.UserAlreadyExist;
import com.flightReservation.Exception.UserDoesNotExist;
import com.flightReservation.entities.Flights;
import com.flightReservation.entities.Tickets;
import com.flightReservation.entities.User;
import com.flightReservation.service.FlightReservationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class FlightReservationApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private FlightReservationServiceImpl flightReservationServiceImpl;
	
	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Fetching resources for testing ...");
	}
	
	@Test
	@DisplayName("User Login Successful")
	@Rollback(true)
	public void authenticateUserTest() throws Exception {
		String message = null;
		try {
			if (flightReservationServiceImpl.authenticateUser("admin","12345").getUserId() == "admin") {
				message ="LoggedIn";
			}
		} catch (InvalidPasswordException exp) {
			message = exp.getMessage();
		}
		String expectedMessage ="LoggedIn";
		assertEquals(message, expectedMessage);

	}
	
	@Test
	@DisplayName("User Login With Invalid Password")
	public void loginWithoutRegTest() {
		assertThrows(InvalidPasswordException.class, () -> {
			flightReservationServiceImpl.authenticateUser("admin","123456");
		});
	}
	
	@Test
	@DisplayName("Is user Id Exist")
	@Rollback(true)
	public void isUserIdExistTest() throws Exception {
		String message = null;
		try {
			if (flightReservationServiceImpl.isUserIdExist("admin") == true) {
				message ="user Exist";
			}
		} catch (InvalidPasswordException exp) {
			message = exp.getMessage();
		}
		String expectedMessage ="user Exist";
		assertEquals(message, expectedMessage);

	}
	
	@Test
	@DisplayName("User Id does not Exist")
	public void isUserIdNotExistTest() {
		assertThrows(UserDoesNotExist.class, () -> {
			flightReservationServiceImpl.findUserById("ayush261");
		});
	}
	
	@Test
	@DisplayName("Is flight Exist")
	@Rollback(true)
	public void getFlightByIdTest() throws Exception {
		String message = null;
		try {
			if (flightReservationServiceImpl.getFlightById("PL104").getFlightNumber() == "PL104") {
				message ="flight found";
			}
		} catch (InvalidPasswordException exp) {
			message = exp.getMessage();
		}
		String expectedMessage ="flight found";
		assertEquals(message, expectedMessage);

	}
	
	@Test
	@DisplayName("Get Flights")
	public void getAllFlightsTest() throws Exception{
		
		assertEquals(flightReservationServiceImpl.getAllFlights("pune", "lucknow", "2020-05-10").size(), 3);
	}
	
	@Test
	@DisplayName("No Flight Found")
	public void getFlightTest() {
		assertThrows(FlightNotFound.class, () -> {
			flightReservationServiceImpl.getAllFlights("pune", "delhi", "2020-05-10" );
		});
	}
	/*@Test
	@DisplayName("Book Ticket")
	@Rollback(true)
	public void bookTicketTest() throws Exception {
		String message = null;
		User user=flightReservationServiceImpl.findUserById("admin");
		Flights flight=flightReservationServiceImpl.getFlightById("PL103");
		Tickets ticket=new Tickets("ayush",21,"Male",user,flight);
		Tickets tickets[]= {ticket};
		
		try {
			if (flightReservationServiceImpl.bookTickets(tickets)==true) {
				message ="Ticket Booked";
			}
		} catch (InvalidPasswordException exp) {
			message = exp.getMessage();
		}
		String expectedMessage ="Ticket Booked";
		assertEquals(message, expectedMessage);

	}*/
	
	@Test
	@DisplayName("Get Booked Tickets")
	@Rollback(true)
	public void getBookedTicketTest() throws Exception {
		
		assertEquals(1, flightReservationServiceImpl.getBookedTickets("admin").size());

	}
	
	@Test
	@DisplayName("No Tickets Found")
	public void getBookedTicketsTest() {
		assertThrows(NoTicketFound.class, () -> {
			flightReservationServiceImpl.getBookedTickets("admin");
		});
	}
}
