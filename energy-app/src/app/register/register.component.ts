import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../core/user/user.service';
import { User } from '../core/user/user';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  username: string = '';
  password : string = '';
  successMsg : string = '';
  errorMsg : string = '';

  constructor(private userService : UserService,
    private router: Router) {}

    tryRegister(): void {
      
      const user: User = {
        userID: undefined,
        role : 'client',
        username: this.username,
        password : this.password
      }

   

      var isPresent: Boolean = false;

      this.userService.findUserByUsername(this.username).subscribe((data: Boolean) => {
        isPresent = data;
        if(isPresent==false) {
          this.userService.register(user).subscribe(data => {
            console.log("Salot");
            console.log(data)
          });

          if(user.userID) {
            localStorage.setItem('userID',user.userID.toString());  
            console.log("user id:", user.userID);
          }
  
          if(user.role) {
            localStorage.setItem('userRole',user.role.toString());
            console.log("user role:", user.role);
          }
          
          //SUCCESS MESSAGE
          this.showSuccess();
          setTimeout(() => {
            this.successMsg = '';
          }, 3000);

          // REDIRECT
          setTimeout(() => {
            this.router.navigate(['Login']);
          }, 1500);
        }
        else {
          this.showError();
          setTimeout(() => {
            this.errorMsg = '';
          }, 3000);
        }
  
      });
      
  }

  showError() {
    this.errorMsg = 'Acest nume de utilizator nu este disponibil!';
  }

  showSuccess() {
    this.successMsg = "V-ati inregistrat cu succes!";
  }
    
}

