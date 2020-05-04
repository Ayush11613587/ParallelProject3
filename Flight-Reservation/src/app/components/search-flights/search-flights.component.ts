import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../first-page/data.service';
import { Flight } from 'src/app/models/flight';
import { FlightService } from 'src/app/service/flight.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-search-flights',
  templateUrl: './search-flights.component.html',
  styleUrls: ['./search-flights.component.css']
})
export class SearchFlightsComponent implements OnInit {

  submitted:boolean=false;
  searchForm:FormGroup;
  flights:Flight[];
  noFlightFound:boolean=false;
  errorNoFlightFound:string;
  //city array used for option in source and destination 
  City: any = ['Pune', 'Delhi', 'Lucknow'];
  //injecting the dependencies
  constructor(private formBuilder:FormBuilder,private userService:UserService,private dataService:DataService,private flightService:FlightService,private router:Router) { }

  ngOnInit(): void {
    if(this.userService.currentUser==null){
      this.router.navigate(["/login"]);
    }
    this.searchForm=this.formBuilder.group({
      source:['',Validators.required],
      destination:['',Validators.required],
      journeyDate:['',Validators.required]
    })
    //setting the values of form which are coming from first page
    this.searchForm.controls.source.setValue(this.dataService.getDetails().controls.source.value);
    this.searchForm.controls.destination.setValue(this.dataService.getDetails().controls.destination.value);
    this.searchForm.controls.journeyDate.setValue(this.dataService.getDetails().controls.journeyDate.value);
    
    this.searchFlights();
  }
  //this function is setting the value of source whenever user change the source from dropdown
  changeSource(e) {
    console.log(e.target.value)
    this.searchForm.controls.source.setValue(e.target.value, {
      onlySelf: true
    })
  }
  //this function is setting the value of destination whenever user change the source from dropdown
  changeDestination(e) {
    console.log(e.target.value)
    this.searchForm.controls.destination.setValue(e.target.value, {
      onlySelf: true
    })
  }
  //this function will search the flights if flights are available as per the user preference
  //then it will intialize the flights array and if no flight found then it will set an error
  searchFlights(){
    this.noFlightFound=false;
    this.submitted=true;
    if(this.searchForm.invalid){
      return;
    }
    //checking whether both source and destination are same or not if same then it will alert
    if(this.searchForm.controls.source.value==this.searchForm.controls.destination.value){
      alert("Source and destination never be same......");
      this.searchForm.reset();
      return;
    }
    this.flightService.getFlights(this.searchForm.controls.source.value,this.searchForm.controls.destination.value,this.searchForm.controls.journeyDate.value).subscribe(
      data=>{
        this.flights=data;
        console.log(this.flights.length);
        
      },err=>{
        if(this.flights.length!=0){
        this.flights.splice(0,this.flights.length);
       }
        this.errorNoFlightFound=err.error.message;
        this.noFlightFound=true;
        console.log(err.error.message);
      }
    )

    
  }
  bookFlight(flight:Flight){
    if(flight.availableSeats==0){
      alert("You can not book ticket as no seat available");
      return;
    }
    this.router.navigate(['confirmation',flight.flightNumber]);
  }
  
}
