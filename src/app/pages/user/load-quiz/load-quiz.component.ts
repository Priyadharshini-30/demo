import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent implements OnInit {

  cid:any;
  quizzes:any;
  constructor(private route:ActivatedRoute, private quiz:QuizService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params)=>{
      this.cid=params['cid'];
    if(this.cid==0)
    {
      this.quiz.quizzes().subscribe(
        (data:any)=>{
          this.quizzes=data;
        },
        (error)=>{
          console.log(error);
        }
      )
    }
    else{
      this.quiz.getQuizzesOfCat(this.cid).subscribe(
        (data:any)=>{
          this.quizzes=data;
          console.log(this.quizzes);
         

        },
        (error)=>{
          console.log(error);
        }
      )
    }
    });
  }

}
