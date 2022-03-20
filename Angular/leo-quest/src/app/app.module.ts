import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CreateComponent } from './createPoll';
import { HomeComponent } from './home';
import { VoteComponent } from './vote';

@NgModule(
  {
    declarations: [
      AppComponent, 
      VoteComponent,
      CreateComponent,
      HomeComponent
    ],
    imports: [
      BrowserModule
    ],
    providers: [],
    bootstrap: [AppComponent]
  }
)
export class AppModule { }
