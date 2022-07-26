import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class CatService {

  constructor(private http:HttpClient) { }
  public categories()
  {
    return this.http.get("http://localhost:8080/category/");
  }

  public addcategory(category:any)
  {
    return this.http.post("http://localhost:8080/category/",category); 
  }
}
