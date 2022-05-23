import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Question } from '../models/question';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  http : HttpClient;
  baseString = environment.baseUrl + "/question";

  constructor(http : HttpClient) {
    this.http = http;
  }

  insertQuestion(q : Question):any{
    return this.http.post(this.baseString, q);
  }

  getQuestionsByQuestionnaireId(qID : number):any{
    return this.http.get<Question[]>(this.baseString + "/questions/" + qID);
  }

  updateQuestion(q : Question):any{
    return this.http.put<Question[]>(this.baseString + "/" + q.id, q);
  }

  deleteQuestion(q : Question){
    return this.http.delete<Question>(this.baseString + "/" + q.id);
  }

}
