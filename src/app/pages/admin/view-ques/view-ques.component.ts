import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-ques',
  templateUrl: './view-ques.component.html',
  styleUrls: ['./view-ques.component.css']
})
export class ViewQuesComponent implements OnInit {

  qid:any;
  title:any;
  questions:any[]=[];
  constructor(private route:ActivatedRoute, private ques:QuestionService,private router:Router) { }

  ngOnInit(): void {
    this.qid=this.route.snapshot.params['qid'];
    this.title=this.route.snapshot.params['title'];
    this.ques.getQuestions(this.qid).subscribe(
      (data:any)=>{
        console.log(data);
        this.questions=data;
      },
      (error)=>{
        console.log(error);
      }
    );

  }
  deleteQuestion(quesId:number)
  {
    Swal.fire({
      icon:'info',
      title:'Are you sure ?',
      confirmButtonText:'Delete',
      showCancelButton:true,
    }).then((result)=>{
      if(result.isConfirmed){
        this.ques.deleteQuestion(quesId).subscribe(
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
