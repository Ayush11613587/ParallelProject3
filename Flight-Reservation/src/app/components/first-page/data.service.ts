import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  searchDetails:FormGroup;
  constructor() { }

  setData(searchDetails:FormGroup){
    this.searchDetails=searchDetails;
    console.log(this.searchDetails.controls.source.value);
  }
  getDetails(){
    return this.searchDetails;
  }
}
