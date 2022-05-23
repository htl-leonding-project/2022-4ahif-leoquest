import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ChosenOption } from '../models/chosen-option';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChosenOptionService {

  http : HttpClient;
  baseString = environment.baseUrl + "/chosenOption";

  constructor(http : HttpClient) {
    this.http = http;
  }

  insertChosenOption(co : ChosenOption):any{
    return this.http.post<ChosenOption>(this.baseString, co);
  }
}
