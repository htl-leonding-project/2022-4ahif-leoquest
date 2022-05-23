import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Survey } from '../models/survey';
import { Transaction } from '../models/transaction';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  http : HttpClient;
  baseString = environment.baseUrl + "/transaction";

  constructor(http : HttpClient) {
    this.http = http;
  }

  generateTransactioncodes(codesanz : number, surveyid : number) : any{
    return this.http.get(this.baseString + "/generate/" + codesanz + "/" + surveyid, {responseType: 'blob' as 'json'});
  }

  getSurveyWithTransactionCode(transaction : Transaction): any{
    return this.http.post<Transaction>(this.baseString + "/getSurvey", transaction);
  }

  getTransactionfromTransactioncode(code : string): any{
    return this.http.get(this.baseString + "/code/"+ code);
  }

  updateTransaction(transaction : Transaction): any{
    return this.http.put(this.baseString + "/"+ transaction.id, transaction);
  }
}
