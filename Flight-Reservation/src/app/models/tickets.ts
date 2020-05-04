import { User } from './user';
import { Flight } from './flight';

export class Tickets{
    ticketNumber:number;
    passengerName:string;
    passengerAge:number;
    passengerGender:string;
    user:User;
    flight:Flight;

    Tickets(ticketNumber:number,passengerName:string,passengerAge:number,passengerGender:string,user:User,flight:Flight){
        this.ticketNumber=ticketNumber;
        this.passengerName=passengerName;
        this.passengerAge=passengerAge;
        this.passengerGender=passengerGender;
        this.user=user;
        this.flight=flight;
    }
}