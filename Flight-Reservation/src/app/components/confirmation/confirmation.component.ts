import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/service/flight.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Tickets } from 'src/app/models/tickets';
import { Flight } from 'src/app/models/flight';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {

  flightNumber:string;
  flight:Flight;
  passengers:Tickets[]=[];
  passengerDetailForm:FormGroup;
  submitted:boolean=false;
  totalCost:number;
  noPassengerAdded:boolean=true;
  isNotEnoughAvailableSeats:boolean=false;
  notEnoughSeats:boolean=false;
  //injecting the dependencies in constructor
  constructor(private flightService:FlightService,private userService:UserService,private router:Router,private route:ActivatedRoute,private formBuilder:FormBuilder) { 
    this.route.params.subscribe(params=>{
      this.flightNumber = params['flightNumber'];
    })
    console.log(this.flightNumber);
    //initializing the flight variable by flightNumber that we got from url
    this.flightService.getFlightById(this.flightNumber).subscribe(data=>{
      this.flight=data;
      if(this.flight.availableSeats<=5){
        this.isNotEnoughAvailableSeats=true;
      }
      //if in case there is no seat available then the addpassenger button will disabled 
      if(this.flight.availableSeats==0){
        this.notEnoughSeats=false;
      }
      console.log(this.flight);
    })
    
  }

  ngOnInit(): void {
    this.passengerDetailForm=this.formBuilder.group({
      //regular expression allows only uppercase and lowercase alphabets and space
      passengerName:['',[Validators.required,Validators.pattern("^[a-zA-Z ]+$")]],
      passengerAge:['',[Validators.required,Validators.max(120)]],
      passengerGender:['',Validators.required]
    })
    //if in case currrentuser is null or the user is not logged in
    if(this.userService.currentUser==null){
      this.router.navigate(["/login"]);
    }
  }
  addPassenger(){
    this.submitted=true;
    if(this.passengerDetailForm.invalid){
      return;
    }
    //adding the passenger in array
    this.passengers.push(this.passengerDetailForm.value);
    this.passengerDetailForm.reset();
    this.submitted=false;
    this.totalCost=this.flight.cost*this.passengers.length;
    this.noPassengerAdded=false;
    if((this.flight.availableSeats-this.passengers.length)==0){
      this.notEnoughSeats=true;
    }
  }
  removePassenger(i:number){
    this.passengers.splice(i,1);
    this.totalCost=this.flight.cost*this.passengers.length;
    if(this.passengers.length==0){
      this.noPassengerAdded=true;
    }
    if((this.flight.availableSeats-this.passengers.length)!=0){
      this.notEnoughSeats=false;
    }
  }
  bookTicket(){
    // if in case user have not add any passenger
    if(this.passengers.length==0){
      // setting the currentuser and flight in every passenger details
      alert("you haven't added any passenger....");
    }
    for(let i=0;i<this.passengers.length;i++){
      this.passengers[i].user=this.userService.currentUser;
      this.passengers[i].flight=this.flight;
    }
    //booking the passengers
    this.flightService.bookTickets(this.passengers).subscribe(data=>{
      if(data==true){
        alert("tickets has been booked successfully....");
        this.router.navigate(['/bookings']);
      }
    },err=>{
      console.log(err.stack);
    })
  }
}
