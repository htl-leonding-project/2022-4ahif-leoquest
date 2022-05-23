import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { DataService } from '../data.service';

@Component({
  selector: 'app-start-survey',
  templateUrl: './start-survey.component.html',
  styleUrls: ['./start-survey.component.scss']
})
export class StartSurveyComponent {

  constructor(public dataS : DataService, public dialogRef: MatDialogRef<StartSurveyComponent>) {}

  start(){
    this.dialogRef.close("Start");
  }

}
