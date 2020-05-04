import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { Tickets } from '../models/tickets';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser:User=null;
  constructor(private http:HttpClient) { }

  addNewUser(newUser:User){
    console.log(newUser);
    return this.http.post("http://localhost:8080/addNewUser",newUser);
  }
  authenticateUser(userId:string,password:string){
    return this.http.get<User>("http://localhost:8080/authenticateUser/"+userId+"/"+password)
  }
  isUserIdExist(userId:string){
    return this.http.get<boolean>("http://localhost:8080/isUserIdExist/"+userId);
  }
  getBookedTickets(){
    if(this.currentUser==null){
      return;
    }
    return this.http.get<Tickets[]>("http://localhost:8080/getBookedTickets/"+this.currentUser.userId);
  }
  cancelTicket(ticket:Tickets){
    return this.http.delete<boolean>("http://localhost:8080/cancelTicket/"+ticket.ticketNumber);
  }
}
