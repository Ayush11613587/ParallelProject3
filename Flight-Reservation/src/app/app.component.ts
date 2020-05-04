import { Component, OnInit } from '@angular/core';
import { UserService } from './service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Flight-Reservation';
  

  constructor(){
    
  }
  ngOnInit(){
    
  }
  
}
