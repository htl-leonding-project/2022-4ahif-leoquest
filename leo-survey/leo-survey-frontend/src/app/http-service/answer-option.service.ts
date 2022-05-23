import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AnswerOption } from '../models/answer-option';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AnswerOptionService {
  http : HttpClient;
  baseString = environment.baseUrl + "/answerOption";

  constructor(http : HttpClient) {
    this.http = http;
  }

  insertAnswerOption(a : AnswerOption){
    return this.http.post(this.baseString, a);
  }

  getAllAnswerOptionsfromQuestionnaire(qnid:number):any{
    return this.http.get<AnswerOption[]>(this.baseString + "/questionnaire/" + qnid);
  }

  updateAnswerOption(ao : AnswerOption){
    return this.http.put<AnswerOption>(this.baseString + "/" + ao.id, ao);
  }

  deleteAO(id: number){
    return this.http.delete<AnswerOption>(this.baseString + "/" + id);
  }
}
