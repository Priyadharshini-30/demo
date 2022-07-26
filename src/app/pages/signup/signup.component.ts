import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService, private snack:MatSnackBar) { }

  public user={
    username:'',
    password:'',
    firstName:'',
    lastName:'',
    userType:'',
    email:'',
    phone:'',
  };

  ngOnInit(): void {
  }

  formSubmit()
  {
    console.log(this.user);
    if(this.user.username=='' || this.user.username==null)
    {
      this.snack.open("username is required",'OK',{
        verticalPosition:'bottom',
        horizontalPosition:'right'
      });
      return;
    }
    this.userService.addUser(this.user).subscribe(
      (data:any)=>{
        console.log(data);
        Swal.fire('Success done!!','User id is '+ data.id+' Success');
      },
      (error)=>
      {
        console.log(error);
        this.snack.open("User already exist",'OK',{
          verticalPosition:'bottom',
          horizontalPosition:'right'
        });
      }
    )

  }


}
