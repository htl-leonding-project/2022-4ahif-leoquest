import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from '../dashboard-fragebogen/dashboard-fragebogen.component';
import { SurveyService } from '../http-service/survey-service.service';
import { Survey } from '../models/survey';

@Component({
  selector: 'app-edit-survey',
  templateUrl: './edit-survey.component.html',
  styleUrls: ['./edit-survey.component.scss']
})
export class EditSurveyComponent {

  public userForm: FormGroup;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData, public dialogRef: MatDialogRef<EditSurveyComponent>, public surveyService: SurveyService) {
    this.userForm = new FormGroup({
      title: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]),
      desc: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(250)])
    });
  }

  update(){
    this.surveyService.update(this.data.survey).subscribe((data: Survey)=>{
      alert(data.title);
      this.dialogRef.close(data);
    });
  }

}
