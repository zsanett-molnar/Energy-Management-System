import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'EnergyApp';
  login : boolean = false;
  isAdminLoggedIn : boolean = false;


  isAuthorizedAdmin : boolean = false;
  isAuthorizedClient : boolean = false;

  constructor(private router:Router) {

  }

  ngOnInit(): void {
    this.login=false;
    this.isAdminLoggedIn = false;
    this.isAuthorizedAdmin = false;
    this.isAuthorizedClient = false;
  }

  goToPage(pageName: string) : void {
    this.router.navigate([`${pageName}`]);
  }

  setAdminIsLoggedIn() : void {
    this.isAdminLoggedIn = true;
  }

  isAdmin() : boolean {
    return this.isAdminLoggedIn;
  }

  isLoggedIn() : boolean {
    return this.login;
  }

  setLoggedIn() : void {
    this.login = true;
  }

  //Authorization
  isAuthorizedA() : boolean {
    return this.isAuthorizedAdmin;
  }

  isAuthorizedC() : boolean {
    return this.isAuthorizedClient;
  }

  setAuthorizedA() : void {
    this.isAuthorizedAdmin = true;
  }

  setAuthorizedC() : void {
    this.isAuthorizedClient = true;
  }

  setLoggedOut() : void {
    this.login = false;
    this.isAdminLoggedIn = false;
    localStorage.removeItem('userID');
  }
}
