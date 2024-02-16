import { Component } from '@angular/core';
import { NgModel } from '@angular/forms';
import { UserService } from '../core/user/user.service';
import { Router } from '@angular/router';
import { ClientOperationsComponent } from '../client-operations/client-operations.component';
import { DeviceService } from '../core/device/device.service';
import { LoginDTO } from '../core/logindto/login-dto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password : string = '';
  errorMsg: string = '';
  successMsg : string = '';

  userID : number | undefined;
  
  constructor(private userService : UserService, private deviceService : DeviceService,
    private router : Router) {}

  
  saveUserID(id : number | undefined) : void {
    this.userID = id;
  }

  // tryLogin(): void {
  //   this.userService.login(this.username, this.password).subscribe(
  //     (data) => {
  //       //this.deviceService.setUsersID(data.userID);
  //       // this.saveUserID(data.userID);
  //       // console.log("sunteti userul cu id-ul", this.userID);
  //       if(data.userID) {
  //         localStorage.setItem('userID',data.userID.toString());  
  //       }

  //       if(data.role) {
  //         localStorage.setItem('userRole',data.role.toString());
  //       }
        
  //       if(data.role == 'admin') {
  //         setTimeout(() => {
  //           this.router.navigate(['Admin-Operations']);
  //         }, 1500);
  //       } 
  //       else  {
  //         setTimeout(() => {
  //           this.router.navigate(['Client-Operations']);
  //         }, 1500);
  //       }

  //       this.showSuccess();
  //       setTimeout(() => {
  //         this.successMsg = '';
  //       }, 3000);
  //     },
  //     (error) => {
  //       console.error(error); 
  
  //       if (error.status === 404) {
  //         this.errorMsg = 'Numele de utilizator sau parola incorecte.';
  //       } else {
  //         this.errorMsg = 'Eroare necunoscută în timpul autentificării.';
  //       }
  
  //       this.showError();
  
  //       setTimeout(() => {
  //         this.errorMsg = '';
  //       }, 3000);
  //     }
  //   );
  // }
  
  showError() {
    this.errorMsg = 'Numele de utilizator sau parola incorecte.';
  }

  showSuccess() {
    this.successMsg = "Autentificare reusita! ☺";
  }
  
  login() {
    const dto : LoginDTO = {
      username : this.username,
      password : this.password
    }

    this.userService.authentification(dto).subscribe(
      (data) => {
        console.log(data);
        if(data.userDto != undefined) {
          if(data.userDto.id != undefined) {
            //console.log("id:",data.userDto?.id);
            localStorage.setItem("userid", data.userDto?.id?.toString());
          }
          if(data.userDto.role != undefined) {
            //console.log("role:",data.userDto?.role);
            localStorage.setItem("role", data.userDto?.role);
          }
          if(data.token != undefined) {
            //console.log("token:", data.token);
            localStorage.setItem("token", data.token);
          }

        }

        if(data.userDto?.role=="admin") {
          setTimeout(() => {
            this.router.navigate(['Admin-Operations']);
          }, 1500);
        }
        else {
          if(data.userDto?.role=="client") {
            setTimeout(() => {
              this.router.navigate(['Client-Operations']);
            }, 1500);
          }
          else {
            console.log("idk");
          }
        }
       
        
        this.showSuccess();
        setTimeout(() => {
          this.successMsg = '';
        }, 3000);
        
      },
  (error) => {
    console.error(error); 
  
    if (error.status === 404) {
      this.errorMsg = 'Numele de utilizator sau parola incorecte.';
    } else {
      this.errorMsg = 'Eroare necunoscută în timpul autentificării.';
    }

    this.showError();

    setTimeout(() => {
      this.errorMsg = '';
    }, 3000);
  });
  }

}
