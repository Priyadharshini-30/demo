import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouteConfigLoadStart, Router } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-ques',
  templateUrl: './add-ques.component.html',
  styleUrls: ['./add-ques.component.css']
})
export class AddQuesComponent implements OnInit {

  qid:any;
  title:any;
  question:any={
    quiz:{

    },
    content:'',
    option1:'',
    option2:'',
    option3:'',
    option4:'',
    answer:''
  };

  constructor(private route:ActivatedRoute,private Question:QuestionService,private router:Router) { }

  ngOnInit(): void {
    this.qid=this.route.snapshot.params['qid'];
    this.title=this.route.snapshot.params['title'];
    console.log(this.qid);
    this.question.quiz['qid']=this.qid;

  }
    formSubmit()
    {
      if(this.question.content.trim()=='' || this.question.content==null)
      {
        return;
      }
      if(this.question.option1.trim()=='' || this.question.option1==null)
      {
        return;
      }
      if(this.question.option2.trim()=='' || this.question.option2==null)
      {
        return;
      }
      if(this.question.answer.trim()=='' || this.question.answer==null)
      {
        return;
      }
      this.Question.addQuestion(this.question).subscribe(
        (data)=>{
          Swal.fire('Success','question added','success');
          this.router.navigate(['/admin/view-questions/'+this.qid+'/'+this.title]);
        },
        (error)=>
        {
          Swal.fire('error','Question not added','error');
        }
      );
      
    }


  

}
