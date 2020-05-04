import { Component, OnInit, Output} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EventEmitter } from 'protractor';
import { DataService } from './data.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-first-page',
  templateUrl: './first-page.component.html',
  styleUrls: ['./first-page.component.css']
})
export class FirstPageComponent implements OnInit {
  searchForm:FormGroup;
  submitted:boolean=false;
  //city array for source nad destination for dropdown
  City: any = ['Pune', 'Delhi', 'Lucknow'];
  //injecting the dependency
  constructor(private formBuilder:FormBuilder,private dataService:DataService,private router:Router,private userService:UserService) { }

  
  ngOnInit(): void {
    this.searchForm=this.formBuilder.group({
      source:['',Validators.required],
      destination:['',Validators.required],
      journeyDate:['',Validators.required]
    })
     if(this.userService.currentUser==null){
       this.router.navigate(["/login"]);
     }
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
  //this function is used for exchanging the values of source and destination i am using 
  //here the concept of third variable swapping 
  swap(){
    let temp=this.searchForm.controls.source.value;
    this.searchForm.controls.source.setValue(this.searchForm.controls.destination.value);
    this.searchForm.controls.destination.setValue(temp);
  }
  
  searchFlights(){
    console.log(this.searchForm.controls.source.value);
    this.submitted=true;
    if(this.searchForm.invalid){
      return;
    }
    //if in case the value of source and destination are same then it will alert 
    // and not allowed the user to book the tickets
    if(this.searchForm.controls.source.value==this.searchForm.controls.destination.value){
      alert("Source and destination never be same......");
      this.searchForm.reset();
      return;
    }
    this.dataService.setData(this.searchForm);
      this.router.navigate(['/search']);
  }
}
