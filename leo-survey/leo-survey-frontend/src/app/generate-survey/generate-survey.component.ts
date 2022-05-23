import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogData } from '../dashboard-fragebogen/dashboard-fragebogen.component';
import { Survey } from '../models/survey';

@Component({
  selector: 'app-generate-survey',
  templateUrl: './generate-survey.component.html',
  styleUrls: ['./generate-survey.component.scss']
})
export class GenerateSurveyComponent implements OnInit {
  public survey : Survey = new Survey();
  public title : string = "";
  public userForm: FormGroup;

  constructor(public _snackBar: MatSnackBar, @Inject(MAT_DIALOG_DATA) public data: DialogData, public dialogRef: MatDialogRef<GenerateSurveyComponent>) {
    this.userForm = new FormGroup({
        title: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]),
        desc: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(250)])
    });
  }

  ngOnInit(): void {
  }

  generate(){
    if(this.survey.title != "" && this.survey.description != ""){
      this.survey.questionnaire = this.data.qn;
      this.dialogRef.close(this.survey);
    }else{
      this._snackBar.open("Title and Description are required", "OK");
    }

    
  }

}
