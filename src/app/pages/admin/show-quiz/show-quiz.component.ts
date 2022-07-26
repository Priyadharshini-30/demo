import { Component, OnInit } from '@angular/core';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-show-quiz',
  templateUrl: './show-quiz.component.html',
  styleUrls: ['./show-quiz.component.css']
})
export class ShowQuizComponent implements OnInit {

  quizzes: any[]=[];
  constructor(private quiz:QuizService) { }

  ngOnInit(): void {
    this.quiz.quizzes().subscribe(
      (data:any)=>{
        this.quizzes=data;
        console.log(this.quizzes);
      },
      (error)=>{
        console.log(error);
        Swal.fire('error');
      }
    )
  }

  deleteQuiz(qid:any)
  {
    Swal.fire({
      icon:'info',
      title:'Are you sure ?',
      confirmButtonText:'Delete',
      showCancelButton:true,
    }).then((result)=>{
      if(result.isConfirmed){
        this.quiz.deleteQuiz(qid).subscribe(
          (data)=>{
            Swal.fire('success','Quiz deleted','success');
          },
          (error)=>{
            Swal.fire('Error','Error in deleting quiz','error')
          }
        )
      }
    })
  }
}
