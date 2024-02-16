import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {

  constructor(private router: Router, public myapp: AppComponent) {}
 
  ngOnInit(): void {
    //setam ca suntem logati
    this.myapp.setLoggedOut();
    localStorage.removeItem("userID");
    localStorage.removeItem("userRole");
    localStorage.removeItem("deviceID");
  }

}
