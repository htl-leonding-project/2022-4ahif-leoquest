import { Injectable } from '@angular/core';
import { Survey } from './models/survey';
import { Transaction } from './models/transaction';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  public transaction : Transaction | undefined;
  public survey : Survey | undefined;

  constructor() { }
}
