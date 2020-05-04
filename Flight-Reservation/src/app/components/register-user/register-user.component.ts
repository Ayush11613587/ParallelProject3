import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  registerForm:FormGroup;
  submitted:boolean=false;
  isUserIdExist:boolean=false;
  userAlreadyExist:string;
  //injecting the dependencies
  constructor(private formBuilder:FormBuilder,private userService:UserService,private router:Router) { }

  ngOnInit() {
    this.registerForm=this.formBuilder.group({
      userId:['',Validators.required],
      firstName:['',[Validators.required,Validators.pattern("^[a-zA-Z]+$")]],
      lastName:['',[Validators.required,Validators.pattern("^[a-zA-Z]+$")]],
      mobileNumber:['',[Validators.required,Validators.pattern("[6-9][0-9]{9}")]],
      gender:['',Validators.required],
      password:['',Validators.required]
    })
    
  }
  //reset the user
  resetUser(){
    this.isUserIdExist=false;
    this.registerForm.markAsUntouched;
  }
  registerUser(){
    this.submitted=true;
    this.isUserIdExist=false;
    if(this.registerForm.invalid){
      return;
    }
    //registering the user if userId is already exist then it will throw an error otherwise it will 
    //register the user na dnavigate to login page
    this.userService.addNewUser(this.registerForm.value).subscribe(data=>{
      
      alert(`${this.registerForm.controls.userId.value} is register successfully ..!`);
      this.router.navigate(['login']);
    },err=>{
      this.isUserIdExist=true;
      this.userAlreadyExist=err.error.message;
    })
  }

}
