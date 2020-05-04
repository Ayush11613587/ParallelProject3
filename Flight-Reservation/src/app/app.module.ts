import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { FirstPageComponent } from './components/first-page/first-page.component';
import { SearchFlightsComponent } from './components/search-flights/search-flights.component';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { UserBookingsComponent } from './components/user-bookings/user-bookings.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterUserComponent,
    SearchFlightsComponent,
    FirstPageComponent,
    NavbarComponent,
    ConfirmationComponent,
    UserBookingsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
