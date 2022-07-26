import { coerceStringArray } from '@angular/cdk/coercion';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CatService } from 'src/app/services/cat.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent implements OnInit {
 

  constructor(private route:ActivatedRoute,private Quiz:QuizService,private cat:CatService,private router:Router) { }

  qid=0;
  quiz: any;
  categories:any;
  ngOnInit(): void {

  this.qid=this.route.snapshot.params['qid'];
  this.Quiz.getQuiz(this.qid).subscribe(
    (data:any)=>{
      this.quiz=data;
      console.log(this.quiz);
    },
    (error)=>{
      console.log(error);
    }
  );

  this.cat.categories().subscribe(
    (data:any)=>{
      this.categories=data;
    },
    (error)=>{
      console.log(error);
    }
  );


  }

  public update()
  {
    this.Quiz.updateQuiz(this.quiz).subscribe(
      (data)=>{
        Swal.fire('success','quiz update success','success').then((e)=>{
          this.quiz=data;
          console.log("update");
          console.log(this.quiz);
          this.router.navigate(['/admin/quizzes']);
        });
      },
      (error)=>
      {
        Swal.fire('error','error in updateing','error');
      }
    );
  }

}
