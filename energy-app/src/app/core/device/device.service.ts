import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Device } from './device';
import { Devicedto } from './devicedto';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  constructor(private httpClient: HttpClient  ) { }

  private baseURL = "http://localhost:8081/device/getByUser";
  private baseURL2 = "http://localhost:8081/device";
  private baseURL3 = "http://localhost:8081/device/freedevices";

  currentUserID : number | undefined;

  setUsersID(id : number | undefined) {
    this.currentUserID=id;
  }

  getUsersDevices(userID : number | undefined) : Observable<Device[]> {
    return this.httpClient.get<Device[]>(`${this.baseURL}/${userID}`);
  }

  getAllDevices() : Observable<Device[]> {
    return this.httpClient.get<Device[]>(`${this.baseURL2}`);
  }

  getDeviceByID(deviceID: number | undefined) : Observable<Device> {
    return this.httpClient.get<Device>(`${this.baseURL2}/${deviceID}`);
  }

  getFreeDevices() : Observable<Device[]> {
    return this.httpClient.get<Device[]>(`${this.baseURL3}`);
  }

  deleteDevice(deviceID: number | undefined) : Observable<Object>  {
    return this.httpClient.delete<Device>(`${this.baseURL2}/${deviceID}`);
  }

  updateDevice(deviceID : number | undefined, deviceDTO : Devicedto) : Observable<Object> {
    return this.httpClient.put<Device>(`${this.baseURL2}/${deviceID}`,deviceDTO);
  }

  createDevice(device : Devicedto) : Observable<Device> {
    return this.httpClient.post<Device>(`${this.baseURL2}`, device);
  }

  
}
