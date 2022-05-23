import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Questionnaire } from '../models/questionnaire';
import { Interviewer } from '../models/interviewer';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {
  http : HttpClient;
  baseString = environment.baseUrl + "/questionnaire";

  constructor(http : HttpClient) {
    this.http = http;
  }

  insertQuestionnaire(q : Questionnaire) : any{
    return this.http.post<Questionnaire>(this.baseString, q);
  }

  getAllPublicAndOwnedQuestionnaires() : any{
    return this.http.get<[Questionnaire]>(this.baseString + "/public/1");
  }

  deleteQuestionnaires(qn : Questionnaire) : any{
    return this.http.delete(this.baseString + "/" + qn.id);
  }

  copyQn(qn : Questionnaire): any{
    return this.http.post(this.baseString + "/duplicateQuestionnaire", qn);
  }

  findByID(id: String) :any{
    return this.http.get(this.baseString + "/id/" + id);
  }

  updateQn(qn : Questionnaire): any{
    return this.http.put<Questionnaire>(this.baseString + "/" + qn.id, qn);
  }
}
