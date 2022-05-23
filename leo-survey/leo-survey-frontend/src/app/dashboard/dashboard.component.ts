import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import { QuestionnaireService } from '../http-service/questionnaire.service';
import { SurveyService } from '../http-service/survey-service.service';
import { Questionnaire } from '../models/questionnaire';
import { Survey } from '../models/survey';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  public questionnaires : Array<Questionnaire> = [];
  public storequestionnaires : Array<Questionnaire> = [];
  public surveysofuser : Array<Survey> = [];
  public filters: Array<String> = ["public", "private", "no-filter"];
  public filter : String = "";

  constructor(public _snackBar: MatSnackBar, public questionnaireservice : QuestionnaireService, public surveyservice : SurveyService) { }

  ngOnInit(): void {
    this._snackBar.open("Erfolgreich eingeloggt", "OK");
    this.questionnaireservice.getAllPublicAndOwnedQuestionnaires().subscribe((data : [Questionnaire] )=> {this.questionnaires = data; this.storequestionnaires = this.questionnaires.slice();});
    this.surveyservice.getSurveyofPerson(1).subscribe((data : [Survey] )=> {this.surveysofuser = data});
  }

  delete(qn : Questionnaire){
    this.questionnaires.splice(this.questionnaires.indexOf(qn),1);
    this.questionnaireservice.deleteQuestionnaires(qn).subscribe();
  }

  copy(q: Questionnaire){
    this.questionnaireservice.copyQn(q).subscribe((data : Questionnaire) =>{
      data.name = "new QN";
      this.storequestionnaires.push(data);
      this.filterSurveyList();
    });
  }

  filterSurvey(q : Questionnaire): Array<Survey>{
    //alert(this.surveysofuser.filter(s => s.questionnaire.id == q.id));
   // var x = this.surveysofuser.filter(s => s.questionnaire.id == q.id);
   //alert("q:" + q.id + " / " + this.surveysofuser[0].questionnaire.id + " / " + x.length);

    return this.surveysofuser.filter(s => s.questionnaire.id == q.id);

  }

  filterSurveyList(){
    switch(this.filter){
      case this.filters[0]:{
        this.questionnaires = this.storequestionnaires.filter(s => s.isPublic == true);
        break;
      }
      case this.filters[1]:{
        this.questionnaires = this.storequestionnaires.filter(s => s.isPublic == false);
        break;
      }
      case this.filters[2]:{
        this.questionnaires = this.storequestionnaires.filter(s => true);
        break;
      }
    }
  }

  iseditable(qn: Questionnaire): Boolean{
    //change after working login
    return (qn.interviewer.id == 1)
  }
}
