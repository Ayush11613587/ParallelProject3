import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup;
  isUserIdExist:boolean=false;
  userIdNotExist:string;
  submitted:boolean=false;
  isPasswordValid:boolean=false;
  invalidPasswordMessage:string;
  //injecting the dependency
  constructor(private formBuilder:FormBuilder,private userService:UserService,private router:Router) { }

  ngOnInit() {
    this.loginForm=this.formBuilder.group({
      userId:['',Validators.required],
      password:['',Validators.required]
    })
  }
  loginUser(){
    this.submitted=true;
    this.isUserIdExist=false;
    this.isPasswordValid=false;
    if(this.loginForm.invalid){
      return;
    }
    //for checking whether the user is exist or not if not it will throw an error
    this.userService.isUserIdExist(this.loginForm.controls.userId.value).subscribe(data=>{
      
    },err=>{
      this.userIdNotExist=err.error.message;
      this.isUserIdExist=true;
      return;
    })
    //after knowing the user is exist then it will first check for password if it is valid then
    //it will intialize the currentuser in userservice otherwise it will set an error
    this.userService.authenticateUser(this.loginForm.controls.userId.value,this.loginForm.controls.password.value).subscribe(data=>{
      this.userService.currentUser=data;
      localStorage.username=this.userService.currentUser.userId;
      console.log(this.userService.currentUser);
      this.router.navigate(['home']);
    },err=>{
      this.isPasswordValid=true;
      this.invalidPasswordMessage=err.error.message;
    })
  }

}
