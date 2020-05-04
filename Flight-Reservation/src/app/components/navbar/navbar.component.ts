import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedin:boolean=false;
  userName:string;
  constructor(private userService:UserService,private router:Router) { }

  ngOnInit(): void {
     if(this.userService.currentUser!=null){
       this.userName=this.userService.currentUser.firstName+" "+this.userService.currentUser.lastName;
       console.log(this.userService.currentUser);
       this.isLoggedin=true;
     }
  }
  logoutUser(){
    if (localStorage.username != null) {
      localStorage.removeItem("username");
      this.userService.currentUser=null;
      this.router.navigate(['/login']);
    }
  }
}
