import { Component, OnInit } from '@angular/core';
import { CatService } from 'src/app/services/cat.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  users:number = 0;
  categories:number = 0;
  quizzes:number = 0;
 

  constructor() { }

  ngOnInit(): void {
  }


  usercountstop:any = setInterval(()=>{
    this.users++;
    
    if(this.users ==60)
    {
      
      clearInterval(this.usercountstop);
    }

  },25)


  catcountstop:any = setInterval(()=>{
    this.categories++;
    if(this.categories == 50)
    {
      
      clearInterval(this.catcountstop);
    }
  },25) 


  quizcountstop:any = setInterval(()=>{
    this.quizzes++;
    if(this.quizzes == 100)
    {
      
      clearInterval(this.quizcountstop);
    }
  },25)

 


}
