import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http:HttpClient) { }

  public quizzes()
  {
    return this.http.get("http://localhost:8080/quiz/");
  }
  public addQuiz(Quiz:any)
  {
    return this.http.post("http://localhost:8080/quiz/",Quiz); 
  }
  public deleteQuiz(qid:number)
  {
    return this.http.delete("http://localhost:8080/quiz/"+qid);
  }
  public getQuiz(qid:number)
  {
    return this.http.get("http://localhost:8080/quiz/"+qid)
  }
  public updateQuiz(quiz:any)
  {
    return this.http.put("http://localhost:8080/quiz/",quiz);
  }

  public getQuizzesOfCat(cid:any)
  {
    return this.http.get("http://localhost:8080/quiz/category/"+cid);
  }
}
