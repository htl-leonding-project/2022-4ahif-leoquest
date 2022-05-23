import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import {SurveyResult} from "../models/survey-result";
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: "root"
})
export class SurveyResultService {

  private baseString = environment.baseUrl + "/survey-result";

  public constructor(private http: HttpClient) {
  }

  public getSurveyResult(surveyId: number): Promise<SurveyResult> {
    return this.http.get(this.baseString + "/id/" + surveyId).toPromise() as Promise<SurveyResult>;
  }
}
