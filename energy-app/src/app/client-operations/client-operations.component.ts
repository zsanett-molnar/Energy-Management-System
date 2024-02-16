import { Component, OnInit } from '@angular/core';
import { Device } from '../core/device/device';
import { DeviceService } from '../core/device/device.service';
import { Router } from '@angular/router';
import {AppComponent} from '../app.component';
import { WebSocketShareService } from '../core/websocket/web-socket-share.service';
import { WebSocketAPI } from '../core/websocket/web-socket-api';

import Chart from 'chart.js/auto';
//or


@Component({
  selector: 'app-client-operations',
  templateUrl: './client-operations.component.html',
  styleUrls: ['./client-operations.component.css']
})
export class ClientOperationsComponent implements OnInit  {
  devices : Device[] | undefined;
  clientID : number | undefined;
  userRole : string | null | undefined;

  // websocketService = new WebSocketShareService();
  // webSocketAPI = new WebSocketAPI(this.websocketService);

  displayNotification : boolean | undefined;
  websocketService : WebSocketShareService = new WebSocketShareService();
  webSocketAPI : WebSocketAPI = new WebSocketAPI(this.websocketService);


  //socket
  wsData: any = 'Hello'; 

  constructor(private deviceService : DeviceService, private router: Router, public myapp: AppComponent) {
    
  }

 
  setUserID() {
    this.clientID = Number(localStorage.getItem('userID'));
  }

  ngOnInit(): void {
    //socket
    this.webSocketAPI._connect();          
    this.onNewValueReceive();
    
    //setam ca suntem logati
    this.myapp.setLoggedIn();
    this.myapp.setAuthorizedC();
    this.clientID = Number(localStorage.getItem('userid'));
    this.userRole = localStorage.getItem('role');
    this.getDevices(this.clientID);
    console.log(this.clientID);

    this.webSocketAPI._connect();          
    this.onNewValueReceive();
  }

  private getDevices(userID : number | undefined) {
    this.deviceService.getUsersDevices(userID).subscribe(data => {
      this.devices = data;
    });
  }

  isAuthorized() {
    if(this.userRole=='client') {
      return true;
    }
    return false;
  }

  //socket
  connect() {
    this.webSocketAPI._connect();
  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  ngOnDestroy() {
   
  }

  isDisplayOn() {
    return this.displayNotification;
  }

  findDeviceById(idToFind: number): boolean {
    if (this.devices) {
        // Folosim metoda find pentru a căuta un dispozitiv cu id-ul dat
        const foundDevice = this.devices.find(device => device.id === idToFind);
        if(foundDevice!=undefined) {
          return true;
        }
        else return false;
        
    } else {
        return false; // sau poți gestiona altfel cazul în care array-ul de dispozitive este undefined
    }
  }

  onNewValueReceive() {
    this.websocketService.getNewValue().subscribe(resp => {
      this.wsData = resp;
      // console.log("wsDATA : ", this.wsData.body);
      if(this.wsData.body.substring(0,7)=='ATENTIE') {
        const id = Number(this.wsData.body.substring(8));  
        if(this.findDeviceById(id)==true){
         
          this.displayNotification=true;
        }       
      }     
    });
  }

  
  goToPage(pageName: string, deviceID : number | undefined) : void {
    if(deviceID!=undefined) {
      localStorage.setItem('deviceID', deviceID.toString());  
      this.router.navigate([`${pageName}`]);
    }
  }

}
