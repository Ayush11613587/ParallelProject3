import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { Tickets } from 'src/app/models/tickets';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-bookings',
  templateUrl: './user-bookings.component.html',
  styleUrls: ['./user-bookings.component.css']
})
export class UserBookingsComponent implements OnInit {

  bookedTickets:Tickets[];
  noTicketsFound:boolean=false;
  errorNoTicketsFound:string;
  //injrcting the dependencies
  constructor(private userService:UserService,private router:Router) { }

  ngOnInit(): void {
    if(this.userService.currentUser==null){
      this.router.navigate(["/login"]);
    }
    //intializing the bookedTickets array
    this.userService.getBookedTickets().subscribe(data=>{
      this.bookedTickets=data;
      console.log(this.bookedTickets);
    },err=>{
      this.errorNoTicketsFound=err.error.message;
      this.noTicketsFound=true;
    })
    
  }
  //on cancelling the ticket first it will confirm the user then it will cancel the ticket
  cancelTicket(ticket:Tickets){
    let result = confirm("Do you want to cancel Ticket?");
if(result){
    this.userService.cancelTicket(ticket).subscribe(
      data=>{
        if(data==true){
          this.userService.getBookedTickets().subscribe(data=>{
            this.bookedTickets=data;
            console.log(this.bookedTickets);
          },err=>{
            this.bookedTickets.splice(0);
            this.errorNoTicketsFound=err.error.message;
            this.noTicketsFound=true;

          })
          
        }
      }
    )
}
    
  }
}
