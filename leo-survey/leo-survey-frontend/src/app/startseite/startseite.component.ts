import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DataService } from '../data.service';
import { TransactionService } from '../http-service/transaction-service.service';
import { Survey } from '../models/survey';
import { Transaction } from '../models/transaction';

@Component({
  selector: 'app-startseite',
  templateUrl: './startseite.component.html',
  styleUrls: ['./startseite.component.scss']
})
export class StartseiteComponent implements OnInit {
  public transactioncode : string = "";

  constructor(public router:Router,public _snackBar: MatSnackBar, public transactionService : TransactionService, public dataS : DataService) {}
  
  ngOnInit(): void {}

  checkTransaction(){
    if(this.transactioncode.length != 14){
      this._snackBar.open("Transactioncode has to be 36 signs", "OK");
    }else{
      this.transactionService.getTransactionfromTransactioncode(this.transactioncode).subscribe((data: Transaction)=>{
        if(data != null){
          this.dataS.transaction = data;
          this.transactionService.getSurveyWithTransactionCode(this.dataS.transaction).subscribe((data : Survey) => {
            this.dataS.survey = data;
              this.router.navigate(["answerSurvey"]);
          });
        }else{
          this._snackBar.open("Transactioncode is wrong", "OK");
        }
      });
    }
  }

}