import { Component } from '@angular/core';
import { DeviceService } from '../core/device/device.service';
import { Router } from '@angular/router';
import { User } from '../core/user/user';
import { UserService } from '../core/user/user.service';
import { Device } from '../core/device/device';
import { Devicedto } from '../core/device/devicedto';

@Component({
  selector: 'app-associate-device',
  templateUrl: './associate-device.component.html',
  styleUrls: ['./associate-device.component.css']
})
export class AssociateDeviceComponent {

  errorMsg : string = "";
  successMsg : string = "";

  devices : Device[] | undefined;
  users : User[] | undefined;

  userIds : number[] | undefined;
  deviceIds : number[] | undefined;

  selectedUserId : number | undefined;
  selectedDeviceId : number | undefined;

  constructor( private deviceService : DeviceService, private userService: UserService,
    private router : Router) {}

    ngOnInit(): void { 
       
      this.getFreeDevices();
      this.getUsers();
    }

  private getUsers() {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
      //console.log("USERS: ", data);
      this.userIds = this.users
        .map(user => user.userID) // Extrage id-urile
        .filter(id => id !== undefined) // Elimină valorile nule
        .map(id => id as number);
      
        console.log("USER IDS", this.userIds);
    })
  }

  private getFreeDevices() {
    this.deviceService.getFreeDevices().subscribe(data => {
      this.devices = data;
      //console.log("DEVICEURI: ", data);
      this.deviceIds = this.devices
        .map(device => device.id) // Extrage id-urile
        .filter(id => id !== undefined) // Elimină valorile nule
        .map(id => id as number);
      
        console.log("DEVICES", this.deviceIds);
    })
  }

  // associateUserToDevice(dev : string | undefined, userId : string | undefined) {
  //   const device = Number(dev);
  //   const user = Number(userId);

  //   console.log("device selectat: ", device);
  //   console.log("user selectat: ", user);
   

  //   this.deviceService.getDeviceByID(device).subscribe(data => {
  //     const deviceDTO : Devicedto = {
  //       description : data.description,
  //       address : data.address,
  //       maxConsumption: data.maxConsumption,
  //       userID : user
  //     }
  
  //     this.deviceService.updateDevice(device, deviceDTO).subscribe(data => {
  //       this.showSuccess();
  //       setTimeout(() => {
  //         this.router.navigate(['Admin-operations']);
  //       }, 3000);
  //     }); 
  //   },
  //   (error) => {
  //       this.showError();
  //       setTimeout(() => {
  //         this.errorMsg='';
  //       }, 3000);
  //   });
  // }

  associateUserToDevice() {

    this.deviceService.getDeviceByID(this.selectedDeviceId).subscribe(data => {
      const deviceDTO : Devicedto = {
        description : data.description,
        address : data.address,
        maxConsumption: data.maxConsumption,
        userID : this.selectedUserId
      }
  
      this.deviceService.updateDevice(this.selectedDeviceId, deviceDTO).subscribe(data => {
        this.showSuccess();
        setTimeout(() => {
          this.router.navigate(['Admin-operations']);
        }, 3000);
      }); 
    },
    (error) => {
        this.showError();
        setTimeout(() => {
          this.errorMsg='';
        }, 3000);
    });
  }


  showError() {
    this.errorMsg = 'A aparut o eroare!';
  }

  showSuccess() {
    this.successMsg = "Asociere reusita! ☺";
  }

  process() {

  }
  
  



}
