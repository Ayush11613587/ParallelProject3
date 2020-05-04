import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Flight } from '../models/flight';
import { Tickets } from '../models/tickets';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http:HttpClient) { }

  getFlights(source:string,destination:string,journeyDate:Date){
   console.log(journeyDate.toString());
    return this.http.get<Flight[]>("http://localhost:8080/flights/"+source+"/"+destination+"/"+journeyDate.toString());
  }
  getFlightById(flightNumber:string){
    return this.http.get<Flight>("http://localhost:8080/flights/"+flightNumber);
  }
  bookTickets(tickets:Tickets[]){
    return this.http.post("http://localhost:8080/bookTickets",tickets);
  }
}
