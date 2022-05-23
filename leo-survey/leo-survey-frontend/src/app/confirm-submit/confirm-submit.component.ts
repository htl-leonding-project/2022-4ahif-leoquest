import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from '../dashboard-fragebogen/dashboard-fragebogen.component';

@Component({
  selector: 'app-confirm-submit',
  templateUrl: './confirm-submit.component.html',
  styleUrls: ['./confirm-submit.component.scss']
})
export class ConfirmSubmitComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData, public dialogRef: MatDialogRef<ConfirmSubmitComponent>) { }

  ngOnInit(): void {
  }

  onSumbmit(){
    this.dialogRef.close("Submit");
  }

}
