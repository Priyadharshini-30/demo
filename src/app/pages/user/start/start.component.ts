import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  qid:any;
  questions:any;
  timer:any;


  marks=0;
  correctAns=0;
  attempted=0;
  isSubmit=false;

  constructor(private loc:LocationStrategy,private route:ActivatedRoute,private ques:QuestionService) { }

  ngOnInit(): void {
    this.preventBackButton();
    this.qid=this.route.snapshot.params['qid'];
    console.log(this.qid);
    this.loadQuestions();

  }
  preventBackButton()
  {
    history.pushState(null,'');
    this.loc.onPopState(()=>{
      history.pushState(null,'');
    });
  }
  loadQuestions()
  {
    this.ques.getQuestionsTest(this.qid).subscribe(
      (data:any)=>{
        console.log(data);
        this.questions=data;
        this.timer=this.questions.length*60;
        this.questions.forEach((q:any)=>{
          q['givenAns']='';
        });
        console.log(this.questions);
        this.startTimer();
      },
      (error)=>{
        console.log(error);
        Swal.fire("Error","Error in loading quiz","error");
      }
    )
  }
  submitQuiz(){
    Swal.fire({
      icon:'info',
      title:'Do you want to submit the quiz?',
      confirmButtonText:'Submit',
      showCancelButton:true,
    }).then((result)=>{
      if(result.isConfirmed){
        this.evalQuiz();
      }
    })
  }

  startTimer()
  {
    let t=window.setInterval(()=>{
      if(this.timer<=0)
      {
          this.evalQuiz();
          clearInterval(t);
      }else{
        this.timer--;
      }
    },1000);
  }

  getFormattedTime()
  {
    let mm=Math.floor(this.timer/60);
    let ss =this.timer-mm*60;
    return mm+" min: "+ss+" sec";

  }
  evalQuiz()
  {
    this.isSubmit=true;
    this.questions.forEach((q:any)=>{
      if(q.givenAns==q.answer)
      {
        this.correctAns++;
        let marksEach=
          this.questions[0].quiz.maxMarks/this.questions.length;
        this.marks+=marksEach;
      }
      if(q.givenAns.trim()!='')
      {
        this.attempted++;
      }
      
    })
    console.log("c a:"+this.correctAns);
      console.log("m:"+this.marks);
  }

}
