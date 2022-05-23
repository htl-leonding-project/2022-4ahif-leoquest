import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Survey } from '../models/survey';
import { Questionnaire } from '../models/questionnaire';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SurveyService {
  http : HttpClient;
  baseString = environment.baseUrl + "/survey";

  constructor(http : HttpClient) {
    this.http = http;
  }

  insertSurvey(survey : Survey) : any{
    return this.http.post<Survey>(this.baseString, survey);
  }

  deleteSurvey(surveyid : number) : any{
    return this.http.delete(this.baseString + "/" + surveyid);
  }

  getSurveyofPerson(interviewerid : number) : any {
    return this.http.get<Survey[]>(this.baseString + "/" + interviewerid);
  }

  getQuestionnaireWithSurveyId(surveyid: number): any{
    return this.http.get<Questionnaire>(this.baseString + "/questionnaire/" + surveyid);
  }

  update(survey : Survey):any{
    return this.http.put<Survey>(this.baseString + "/" + survey.id, survey);
  }

  evaluate(id : number){
    return this.http.get(this.baseString + "/evaluation/" + id, {responseType: 'blob' as 'json'});
  }

  evaluate2(id : number){
    return this.http.get(this.baseString + "/evaluation/" + id, {responseType: 'blob' as 'json'});
  }

}
