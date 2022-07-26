import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http:HttpClient) { }

  public getQuestions(qid:number)
  {
    return this.http.get("http://localhost:8080/question/quiz/"+qid);
  }

  public getQuestionsTest(qid:number)
  {
    return this.http.get("http://localhost:8080/question/quiz/"+qid);
  }

  public addQuestion(question:any)
  {
    return this.http.post("http://localhost:8080/question/",question); 
  }

  public deleteQuestion(quesId:number)
  {
    return this.http.delete("http://localhost:8080/question/"+quesId);
  }

}
