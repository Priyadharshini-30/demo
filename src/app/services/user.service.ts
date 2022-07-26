import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  public addUser(user: any)
  {
    return this.http.post("http://localhost:8080/user/signup",user); 
  }

  public typeUser(user: any)
  {
    return this.http.post("http://localhost:8080/user/type",user); 
  }

  loggedIn()
  {
    return !!localStorage.getItem('token');
  }

  logoutUser()
  {
    localStorage.removeItem('token');
  }


  getToken(){
    return localStorage.getItem('token')
  }

}
