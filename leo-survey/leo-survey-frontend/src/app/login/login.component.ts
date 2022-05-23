import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  hide = true;

  constructor(public router:Router) { }

  ngOnInit(): void {
  }
  
  login(){
    //Test

    //route to dashboard
    
    this.router.navigate(['/dashboard']);
  }

}
