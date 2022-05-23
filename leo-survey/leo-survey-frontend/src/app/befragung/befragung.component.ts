import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditSurveyComponent } from '../edit-survey/edit-survey.component';
import { SurveyService } from '../http-service/survey-service.service';
import { TransactionService } from '../http-service/transaction-service.service';
import { Survey } from '../models/survey';
import { TransactionComponent } from '../transaction/transaction.component';
import { ViewSurveyComponent } from '../view-survey/view-survey.component';

@Component({
  selector: 'app-befragung',
  templateUrl: './befragung.component.html',
  styleUrls: ['./befragung.component.scss']
})
export class BefragungComponent implements OnInit {

  @Input() survey!: Survey;
  @Output() loeschen : EventEmitter<Survey> = new EventEmitter<Survey>();

  constructor(public generateTransactionCodes: MatDialog, public editSurveyComponent: MatDialog, public viewSurvey: MatDialog ,public transactionservice : TransactionService, public surveyService : SurveyService) { }

  ngOnInit(): void {
  }

  onDelete(){
    this.loeschen.emit(this.survey);
  }

  view(){
    const dialogRef = this.viewSurvey.open(ViewSurveyComponent, {width:"30%",
    data: {
      survey : this.survey
    },});
  }

  editSurvey(){
    const dialogRef = this.editSurveyComponent.open(EditSurveyComponent, {width:"30%",
    data: {
      survey : this.survey
    },});

    dialogRef.afterClosed().subscribe(result => {
      if((typeof result) == "object"){
        this.survey = result;
      }
    });
  }

  evaluateSurvey(){
    this.surveyService.evaluate(this.survey.id).subscribe((response : any)=>{
      let dataType = response.type;
        let binaryData = [];
        binaryData.push(response);
        let downloadLink = document.createElement('a');
        downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
        if ("transactionCodes.csv"){
            downloadLink.setAttribute('download', "evaluation_" + this.survey.title + ".csv");
            document.body.appendChild(downloadLink);
            downloadLink.click();
        }
    });
  }

  evaluateSurvey2(){
    this.surveyService.evaluate2(this.survey.id).subscribe((response : any)=>{
      let dataType = response.type;
        let binaryData = [];
        binaryData.push(response);
        let downloadLink = document.createElement('a');
        downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
        if ("transactionCodes.csv"){
            downloadLink.setAttribute('download', "evaluation2_" + this.survey.title + ".csv");
            document.body.appendChild(downloadLink);
            downloadLink.click();
        }
    });
  }

  generateCodes(){
    const dialogRef = this.generateTransactionCodes.open(TransactionComponent,{width:"30%"});

    dialogRef.afterClosed().subscribe(result => {
      if((typeof result) == "number"){
        this.transactionservice.generateTransactioncodes(result, this.survey.id).subscribe((response : any)=>{
          let dataType = response.type;
            let binaryData = [];
            binaryData.push(response);
            let downloadLink = document.createElement('a');
            downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
            if ("transactionCodes.csv"){
                downloadLink.setAttribute('download', "transactionCodes_" + this.survey.title + ".csv");
                document.body.appendChild(downloadLink);
                downloadLink.click();
            }
        });
      }
    });
  }

}
