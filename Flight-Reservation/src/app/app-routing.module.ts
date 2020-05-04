import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { FirstPageComponent } from './components/first-page/first-page.component';
import { SearchFlightsComponent } from './components/search-flights/search-flights.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { UserBookingsComponent } from './components/user-bookings/user-bookings.component';


const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterUserComponent},
  {path:'home',component:FirstPageComponent},
  {path:'search',component:SearchFlightsComponent},
  {path:'confirmation/:flightNumber',component:ConfirmationComponent},
  {path:'bookings',component:UserBookingsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
