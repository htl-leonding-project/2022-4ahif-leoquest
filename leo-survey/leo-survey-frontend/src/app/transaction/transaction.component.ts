import { Component} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent {
  tansactionscodeanz : number = 20;
  public userForm : FormGroup;

  constructor(public dialogRef: MatDialogRef<TransactionComponent>) {
    this.userForm = new FormGroup({
      codeanz: new FormControl('', [Validators.required, Validators.min(1), Validators.max(100)])
    });
  }

  generateCodes(){
    this.dialogRef.close(this.tansactionscodeanz);
  }
    
}
