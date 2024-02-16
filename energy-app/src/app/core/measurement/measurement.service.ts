import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Measurement } from './measurement';

@Injectable({
  providedIn: 'root'
})
export class MeasurementService {

  constructor(private httpClient: HttpClient) { }

  private baseURL = "http://localhost:8082/measurement/deviceid";

  getDeviceMeasurements(deviceId : number | undefined) : Observable<Measurement[]> {
    console.log("URL:",`${this.baseURL}/${deviceId}`);
    return this.httpClient.get<Measurement[]>(`${this.baseURL}/${deviceId}`);
  }
}
