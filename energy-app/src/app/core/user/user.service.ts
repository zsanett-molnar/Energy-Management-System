import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Userdto } from './userdto';
import { LoginDTO } from '../logindto/login-dto';
import { LoginResponse } from '../loginresponse/login-response';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:8080/user/login";
  private baseURL2 = "http://localhost:8080/user";
  private baseURL3 = "http://localhost:8080/user/register";
  private baseURL4 = "http://localhost:8080/user/getAll";

  private loginURL = "http://localhost:8080/login";
  private getAdminIdURL = "http://localhost:8080/user/getAdmin";

  constructor(private httpClient : HttpClient) { }

  getAllUsers() : Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.baseURL4}`);
  }


  login(username: String, password : String) : Observable<User> {
    console.log("URL TRANSMIS:", `${this.baseURL}/${username}/${password}` );
    return this.httpClient.get<User>(`${this.baseURL}/${username}/${password}`);
  }

  register(user : User) : Observable<User> {
    return this.httpClient.post<User>(`${this.baseURL2}`, user );
  }

  findUserByUsername(username : String) : Observable<Boolean> {
    return this.httpClient.get<Boolean>(`${this.baseURL3}/${username}`);
  }

  updateUser(userID : number | undefined, userDTO : Userdto) : Observable<Object> {
    return this.httpClient.put<User>(`${this.baseURL2}/${userID}`, userDTO);
  }

  deleteUserByID(userID : number | undefined) : Observable<Object> {
    return this.httpClient.delete<User>(`${this.baseURL2}/${userID}`);
  }
 
  getToken() {
    return localStorage.getItem('token');
  }

  authentification(dto : LoginDTO) : Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>(`${this.loginURL}`, dto);
  }

  getAdminID() : Observable<User> {
    return this.httpClient.get<User>(`${this.getAdminIdURL}`);
  }
}
