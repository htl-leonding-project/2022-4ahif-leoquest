import { Component} from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-delete',
  templateUrl: './confirm-delete.component.html',
  styleUrls: ['./confirm-delete.component.scss']
})
export class ConfirmDeleteComponent{

  constructor(public dialogRef: MatDialogRef<ConfirmDeleteComponent>) { }

  onDelete(){
    this.dialogRef.close("Löschen");
  }

}
