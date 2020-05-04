import { Tickets } from './tickets';

export class Flight{
    flightNumber:String;
    flightCompany:String;
    source:String;
    destination:String;
    cost:number;
    journeyDate:Date;
    availableSeats:number;
    tickets:Tickets[];

    Flight(flightNumber:String,flightCompany:String,source:String,destination:String,cost:number,journeyDate:Date,availableSeats:number){
       this.flightNumber=flightNumber;
       this.flightCompany=flightCompany;
       this.source=source;
       this.destination=destination;
       this.cost=cost;
       this.journeyDate=journeyDate;
       this.availableSeats=availableSeats;
       
    }
}