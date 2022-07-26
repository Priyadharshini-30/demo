import { Component, OnInit } from '@angular/core';
import { CatService } from 'src/app/services/cat.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-cat',
  templateUrl: './view-cat.component.html',
  styleUrls: ['./view-cat.component.css']
})
export class ViewCatComponent implements OnInit {

  
  categories: any[]=[];
  constructor(private cat:CatService) { 
   
  }

  ngOnInit(): void {
    this.cat.categories().subscribe((data:any)=>{
      this.categories=data;
    },
    (error)=>{
      console.log(error);
      Swal.fire("Error");
    }
  )}

}
