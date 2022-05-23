import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from '../dashboard-fragebogen/dashboard-fragebogen.component';
import { QuestionService } from '../http-service/question.service';
import { Question } from '../models/question';

@Component({
  selector: 'app-view-questionnaire',
  templateUrl: './view-questionnaire.component.html',
  styleUrls: ['./view-questionnaire.component.scss']
})
export class ViewQuestionnaireComponent implements OnInit {
  public questions : Array<Question> = []

  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData, public questionservice : QuestionService) {}

  ngOnInit(): void {
    this.questionservice.getQuestionsByQuestionnaireId(this.data.qn.id).subscribe((data : any) => {this.questions = data});
  }

}
