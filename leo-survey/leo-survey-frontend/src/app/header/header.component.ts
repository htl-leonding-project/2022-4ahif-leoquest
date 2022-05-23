import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  constructor(public login: MatDialog, public router:Router) { }

  ngOnInit(): void {
  }

  openLogin(){
    const dialogRef = this.login.open(LoginComponent,{width:"40%"});
    
    /*
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
    */
  }

}
