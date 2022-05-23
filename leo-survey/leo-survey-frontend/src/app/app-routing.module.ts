import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnswerSurveyComponent } from './answer-survey/answer-survey.component';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EditQuestionnaireComponent } from './edit-questionnaire/edit-questionnaire.component';
import { FragebogenErstellenComponent } from './fragebogen-erstellen/fragebogen-erstellen.component';
import { StartseiteComponent } from './startseite/startseite.component';
import {SurveyResultComponent} from "./survey-result/survey-result.component";

const routes: Routes = [
  {path: '', component: StartseiteComponent},
  {path: 'startseite', component: StartseiteComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'survey-result/:surveyIdParam', component: SurveyResultComponent},
  {path: 'fragebogen-erstellen', component: FragebogenErstellenComponent},
  {path: 'dashboard/fragebogen-editieren/:id', component: EditQuestionnaireComponent},
  {path: 'answerSurvey', component: AnswerSurveyComponent},
  {path: '**', component: StartseiteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
