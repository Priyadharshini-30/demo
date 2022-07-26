import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CatService } from 'src/app/services/cat.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {

  categories: any[]=[];
  quiz={
    title:'',
    description:'',
    maxMarks:'',
    noOfQuestions:'',
    active:true,
    category:{
      cid:'',
    }

  };
  constructor(private cat:CatService,private snack:MatSnackBar,private Quiz:QuizService) { }

  ngOnInit(): void {
    this.cat.categories().subscribe(
      (data:any)=>{
        this.categories=data;
        console.log(this.categories);
      },
      (error)=>{
        console.log(error);
        Swal.fire('error');
      }
    )
  }

    addQuiz()
    {
      if(this.quiz.title.trim()=='' || this.quiz.title==null)
      {
         this.snack.open('Title required','',{
           duration:3000,
         });
         return;
      }
      this.Quiz.addQuiz(this.quiz).subscribe(
      (data:any)=>{   
        this.quiz={
          title:'',
          description:'',
          maxMarks:'',
          noOfQuestions:'',
          active:true,
          category:{
            cid:'',
          }
      
        };
        console.log(this.quiz);
        Swal.fire('Success','Quiz added','success');
        
      },
      (error)=>{
        Swal.fire('error','Server error','error');
      }
      )
    }
  


}
