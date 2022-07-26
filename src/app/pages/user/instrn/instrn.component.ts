import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instrn',
  templateUrl: './instrn.component.html',
  styleUrls: ['./instrn.component.css']
})
export class InstrnComponent implements OnInit {
  qid:any;
  Quiz:any;
  constructor(private route:ActivatedRoute, private quiz:QuizService,private Router:Router) { }

  ngOnInit(): void {
    this.qid=this.route.snapshot.params['qid'];

    this.quiz.getQuiz(this.qid).subscribe(
      (data:any)=>{
        console.log(data);
        this.Quiz=data;
      },
      (error)=>{
          console.log(error);
      }
    )
  }
  startQuiz(){
    Swal.fire({
      title:'Do you want to start the quiz?',
      showCancelButton:true,
      confirmButtonText:'Start',
      denyButtonText:'Do not save',
      icon:'info',
    }).then((result)=>{
      if(result.isConfirmed){
        this.Router.navigate(['/user/start/'+this.qid]);
      }else if(result.isDenied){
        Swal.fire('Changes are not saved','','info');
      }
    })
  }

}
