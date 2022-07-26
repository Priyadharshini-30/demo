import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CatService } from 'src/app/services/cat.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-sidebar',
  templateUrl: './user-sidebar.component.html',
  styleUrls: ['./user-sidebar.component.css']
})
export class UserSidebarComponent implements OnInit {

  categories:any=[];
  cid:any;
  constructor(private cat:CatService,private snack:MatSnackBar, public auth:UserService) { }

  ngOnInit(): void {
    this.cat.categories().subscribe(
      (data:any)=>{
        this.categories=data;
        console.log("i am work");
        console.log(this.categories);
        this.cid=this.categories.cid;
       if(this.cid==undefined)
       {
        this.cid=0;
       }
      },
      (error)=>{
        this.snack.open('error in loading categories','',{
          duration:3000,
        })
      }
    );
  }

}
