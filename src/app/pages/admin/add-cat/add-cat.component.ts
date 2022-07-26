import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CatService } from 'src/app/services/cat.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-cat',
  templateUrl: './add-cat.component.html',
  styleUrls: ['./add-cat.component.css']
})
export class AddCatComponent implements OnInit {

  public category={
    titile:'',
    description:''
  };
  constructor(private cat:CatService,private snack:MatSnackBar) { }

  ngOnInit(): void {
  }

  formSubmit()
  {
    if(this.category.titile.trim()=='' || this.category.titile==null)
    {
         this.snack.open('Title required','',{
           duration:3000,
         });
         return;
    }
    this.cat.addcategory(this.category).subscribe(
      (data:any)=>{   
        console.log(this.category);
        Swal.fire('Success','Category added','success');
        
      },
      (error)=>{
        Swal.fire('error','Server error','error');
      }
    )
  }

}
