import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Answer } from '../models/answer';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  http : HttpClient;
  baseString = environment.baseUrl + "/answer";

  constructor(http : HttpClient) {
    this.http = http;
  }

  insertAnswer(a : Answer):any{
    return this.http.post<Answer>(this.baseString, a);
  }
}
