import { HttpClientJsonpModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData={
    username:'',
    password:''
  };
  info:any;

  
  constructor(private snack:MatSnackBar, private login:LoginService,private user:UserService,
    private router:Router) { }

  ngOnInit(): void {
  }

  formSubmit()
  {
    console.log(this.loginData);
    if(this.loginData.username.trim()=='' || this.loginData.username==null)
    {
      this.snack.open("username is required",'OK',{
        verticalPosition:'bottom',
        horizontalPosition:'right'
      });
      return;
    }
    if(this.loginData.password.trim()=='' || this.loginData.password==null)
    {
      this.snack.open("password is required",'OK',{
        verticalPosition:'bottom',
        horizontalPosition:'right'
      });
      return;
    }
  

    this.login.generateToken(this.loginData).subscribe(
      (data:any)=>{
        console.log(data);
        localStorage.setItem('token',data);
        this.user.typeUser(this.loginData).subscribe(
          (data)=>{
            this.info=data;
            console.log(this.info.userType);
            if(this.info.userType=='user')
            {
              this.router.navigate(['/user']);
            }
            else
            {
              this.router.navigate(['/admin']);
            }
          }
        )  
      },
      (error)=>
      {
        console.log(error);
        this.snack.open("Register Userself and then login",'OK',{
          verticalPosition:'bottom',
          horizontalPosition:'right'
        });
      }
    )
     

    }
   
    
}
