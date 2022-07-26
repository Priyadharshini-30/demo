import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-ans',
  templateUrl: './ans.component.html',
  styleUrls: ['./ans.component.css']
})
export class AnsComponent implements OnInit {

  questions:any[]=[];
  qid:any;
  constructor(private loc:LocationStrategy,private ques:QuestionService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.preventBackButton();
    this.qid=this.route.snapshot.params['qid'];
    this.ques.getQuestions(this.qid).subscribe(
      (data:any)=>{
        this.questions=data;
        console.log(this.qid);

      }
    )
  }
  preventBackButton()
  {
    history.pushState(null,'');
    this.loc.onPopState(()=>{
      history.pushState(null,'');
    });
  }
  



}
