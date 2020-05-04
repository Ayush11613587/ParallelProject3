import { Tickets } from './tickets';

export class User{
    userId:string;
    firstName:String;
    lastName:String;
    gender:String;
    mobileNumber:number;
    password:String;
    tickets:Tickets[];
    User(userId:string,firstName:String,lastName:String,gender:String,mobileNumber:number,password:String,tickets:Tickets[]){
        this.userId=userId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.mobileNumber=mobileNumber;
        this.password=password;
        this.tickets=tickets;
    }
}