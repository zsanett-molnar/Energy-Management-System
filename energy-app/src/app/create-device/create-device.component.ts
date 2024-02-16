import { Component } from '@angular/core';
import { DeviceService } from '../core/device/device.service';
import { Router } from '@angular/router';
import { Device } from '../core/device/device';
import { Devicedto } from '../core/device/devicedto';

@Component({
  selector: 'app-create-device',
  templateUrl: './create-device.component.html',
  styleUrls: ['./create-device.component.css']
})
export class CreateDeviceComponent {
  description: string = '';
  address : string = '';
  maxConsumption : number | undefined;
  userID : number | undefined;

  successMsg : string = '';
  errorMsg : string = '';

  constructor( private deviceService : DeviceService,
    private router : Router) {}

  
  createDevice(description : string | undefined, address : string | undefined, maxConsumption : number | undefined,
    userID : number | undefined) : void {
    const deviceDTO : Devicedto = {
      description : description,
      address : address,
      maxConsumption: maxConsumption,
      userID: userID
    }
    this.deviceService.createDevice(deviceDTO).subscribe(
      (data) => {
        this.showSuccess();
        setTimeout(() => {
            this.router.navigate(['Admin-Operations']);
          }, 1500);
      },
      (error) => {
        setTimeout(() => {
          this.showError();
        }, 1500);
      });
  }

  showSuccess() {
    this.successMsg = "Device inserat cu succes!";
  }

  showError() {
    this.errorMsg = "Nu exista user cu acest ID!";
  }
}
