package com.flightReservation.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<?> handleUserAlreadyExist(UserAlreadyExist exception,WebRequest request){
		ErrorResponseDetails errorResponseDetails=new ErrorResponseDetails(exception.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorResponseDetails,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> handleUserDoesNotExist(UserDoesNotExist exception,WebRequest request){
		ErrorResponseDetails errorResponseDetails=new ErrorResponseDetails(exception.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorResponseDetails,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> handleInvalidPasswordException(InvalidPasswordException exception,WebRequest request){
		ErrorResponseDetails errorResponseDetails=new ErrorResponseDetails(exception.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorResponseDetails,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> handleFlightNotFound(FlightNotFound exception,WebRequest request){
		ErrorResponseDetails errorResponseDetails=new ErrorResponseDetails(exception.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorResponseDetails,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> handleNoTicketFound(NoTicketFound exception,WebRequest request){
		ErrorResponseDetails errorResponseDetails=new ErrorResponseDetails(exception.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorResponseDetails,HttpStatus.NOT_FOUND);
	}
}
