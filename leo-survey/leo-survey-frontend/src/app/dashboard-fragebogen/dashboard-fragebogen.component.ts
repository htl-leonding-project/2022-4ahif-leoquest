import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ConfirmDeleteComponent } from '../confirm-delete/confirm-delete.component';
import { GenerateSurveyComponent } from '../generate-survey/generate-survey.component';
import { SurveyService } from '../http-service/survey-service.service';
import { TransactionService } from '../http-service/transaction-service.service';
import { Interviewer } from '../models/interviewer';
import { Questionnaire } from '../models/questionnaire';
import { Survey } from '../models/survey';
import { TransactionComponent } from '../transaction/transaction.component';
import { ViewQuestionnaireComponent } from '../view-questionnaire/view-questionnaire.component';

export interface DialogData {
  qn: Questionnaire;
  survey : Survey;
  notanswered : number;
  questions: number
}

@Component({
  selector: 'app-dashboard-fragebogen',
  templateUrl: './dashboard-fragebogen.component.html',
  styleUrls: ['./dashboard-fragebogen.component.scss']
})
export class DashboardFragebogenComponent implements OnInit {
  @Input() questionnaire!: Questionnaire;
  @Input() editable!: Boolean;
  @Input() surveysofQuestionnaire!: Survey[];
  @Output() loeschen : EventEmitter<Questionnaire> = new EventEmitter<Questionnaire>();
  @Output() getsurvey : EventEmitter<Survey> = new EventEmitter<Survey>();
  @Output() copy: EventEmitter<Questionnaire> = new EventEmitter<Questionnaire>();

  constructor(public router:Router, public viewqn: MatDialog, public deleteconfirmation: MatDialog, public generatesurvey: MatDialog, public surveyservice : SurveyService) { }

  ngOnInit(): void {
  }

  deleteqn(){
    const dialogRef = this.deleteconfirmation.open(ConfirmDeleteComponent, {width:"30%"});

    dialogRef.afterClosed().subscribe(result => {
      if((typeof result) ==  "string" && result == "Löschen"){
        this.loeschen.emit(this.questionnaire);
      }
    });  
  }

  viewQuestionnaire(){
    const dialogRef = this.viewqn.open(ViewQuestionnaireComponent, {width:"40%",
    data: {
      qn : this.questionnaire
    },});
  }

  openTransactionDialog(){
    const dialogRef = this.generatesurvey.open(GenerateSurveyComponent, {width:"30%", 
    data: {
      qn : this.questionnaire
    },});

    dialogRef.afterClosed().subscribe(result => {
      if((typeof result) == "object"){
        this.surveyservice.insertSurvey(result).subscribe((data : Survey) => {
          this.surveysofQuestionnaire.push(data);
          window.location.reload();
        });
      }
    });
  }

  onDelete(survey : Survey){
    const dialogRef = this.deleteconfirmation.open(ConfirmDeleteComponent, {width:"30%"});

    dialogRef.afterClosed().subscribe(result => {
      if((typeof result) ==  "string" && result == "Löschen"){
        this.surveyservice.deleteSurvey(survey.id).subscribe((data : any) => {
          this.surveysofQuestionnaire.splice(this.surveysofQuestionnaire.indexOf(survey), 1);
          window.location.reload();// splice geht ned 
        });
      }
    }); 
  }

  copyQuestionnaire(){
    this.copy.emit(this.questionnaire);
  }

}
