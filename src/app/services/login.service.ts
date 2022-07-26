import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public generateToken(loginData:any)
  {
    return this.http.post('http://localhost:8080/authenticate',loginData,{responseType:'text'})
  }
   
  
}
