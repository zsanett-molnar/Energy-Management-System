import { Component } from '@angular/core';
import { Device } from '../core/device/device';
import { DeviceService } from '../core/device/device.service';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { Devicedto } from '../core/device/devicedto';
import { User } from '../core/user/user';
import { UserService } from '../core/user/user.service';
import { Userdto } from '../core/user/userdto';

@Component({
  selector: 'app-admin-operations',
  templateUrl: './admin-operations.component.html',
  styleUrls: ['./admin-operations.component.css']
})
export class AdminOperationsComponent {
  devices : Device[] | undefined;
  users : User[] | undefined;

  //local storage elements
  userID : number | undefined;
  userRole : string | null | undefined;

  isEditingDevice : boolean = false;
  isEditingUser : boolean = false;

  //date pentru create device
  deviceID: number | undefined;
  description : string | undefined;
  address : string | undefined;
  maxConsumption : string | undefined;

  constructor(private deviceService : DeviceService, private userService : UserService, private router: Router, public myapp: AppComponent) {}

  ngOnInit(): void {
    //local storage
    this.userID = Number(localStorage.getItem('userID'));
    //this.userRole = localStorage.getItem('userRole');
    //setam ca suntem logati
    this.isEditingDevice = false;
    this.isEditingUser = false;
    this.myapp.setLoggedIn();
    this.myapp.setAuthorizedA();
    this.myapp.setAdminIsLoggedIn();
    // if(this.userRole=='admin'){
    //   this.myapp.setAdminIsLoggedIn();
    //   this.myapp.setAuthorizedA();
    // }
    
    this.getDevices();
    this.getUsers();
    
  }

  isAuthorized() {
    if(this.userRole=='admin') {
      return true;
    }
    return false;
  }

  private getDevices() {
    this.deviceService.getAllDevices().subscribe(data => {
      this.devices = data;
      console.log(data);
    });
  }


  private getUsers() {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
      console.log(data);
    })

  }

  deleteDeviceById(deviceID: number | undefined) : void{
    //console.log("a intrat in delete, idul este:", deviceID);
    const deletedDevice = this.deviceService.deleteDevice(deviceID).subscribe(data=> {
      this.deviceService.getAllDevices().subscribe(data => {
        this.devices = data;
        console.log(data);
      });
    });
    //console.log(deletedDevice);
  }

  updateDevice(deviceID : number|undefined, description : string | undefined, address : string | undefined, 
    maxConsumption : number | undefined, userID : number | undefined) : void {
      
      console.log("Undefined?:", userID);
      if(userID==undefined) {

      }
      const deviceDTO : Devicedto = {
        description : description,
        address : address,
        maxConsumption: maxConsumption,
        userID : userID
      }

      const updatedDevice = this.deviceService.updateDevice(deviceID, deviceDTO).subscribe(data=> {
        this.deviceService.getAllDevices().subscribe(data => {
          this.devices = data;
          //console.log(data);
        });
      });;
    }

    isEditingUserModeOn() : boolean {
      return this.isEditingUser;
    }

    isEditingModeOn() : boolean {
      return this.isEditingDevice;
    }

    setEditingMode() {
      this.isEditingDevice = true;
    }

    setEditingModeUsers() {
      this.isEditingUser = true;
    }

    cancelEditing() {
      this.isEditingDevice = false;
      this.isEditingUser = false;
    }

    

    updateUser(userID : number|undefined, role : string | undefined, username : string | undefined, 
      password : string | undefined) : void {
        
        
        const userDTO : Userdto = {
          role : role,
          username : username,
          password : password
        }
  
        const updatedDevice = this.userService.updateUser(userID, userDTO).subscribe(data=> {
          this.userService.getAllUsers().subscribe(data => {
            this.users = data;
            //console.log(data);
          });
        });;
      }

    deleteUser(userID : number | undefined) : void {
      this.userService.deleteUserByID(userID).subscribe(data => {
        this.userService.getAllUsers().subscribe(data => {
          this.users = data;
          //console.log(data);
        });
      })
    }

}
