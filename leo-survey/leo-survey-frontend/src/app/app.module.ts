import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StartseiteComponent } from './startseite/startseite.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { HeaderComponent } from './header/header.component';
import { FragebogenErstellenComponent } from './fragebogen-erstellen/fragebogen-erstellen.component';
import {MatStepperModule} from '@angular/material/stepper';
import { FrageErstellenComponent } from './frage-erstellen/frage-erstellen.component';
import {MatCardModule} from '@angular/material/card';
import {MatSelectModule} from '@angular/material/select';
import { AnswerOptionErstellenComponent } from './answer-option-erstellen/answer-option-erstellen.component';
import { FragePruefenComponent } from './frage-pruefen/frage-pruefen.component';
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { QuestionnaireService } from './http-service/questionnaire.service';
import { AnswerOptionService } from './http-service/answer-option.service';
import { QuestionService } from './http-service/question.service';
import { DashboardFragebogenComponent } from './dashboard-fragebogen/dashboard-fragebogen.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { TransactionComponent } from './transaction/transaction.component';
import { SurveyService } from './http-service/survey-service.service';
import { TransactionService } from './http-service/transaction-service.service';
import { BefragungComponent } from './befragung/befragung.component';
import { ConfirmDeleteComponent } from './confirm-delete/confirm-delete.component';
import {SurveyResultComponent} from "./survey-result/survey-result.component";
import {SurveyResultService} from "./http-service/survey-result.service";
//import { NgxEchartsModule } from 'ngx-echarts';
import {MatExpansionModule} from '@angular/material/expansion';
import { ViewQuestionnaireComponent } from './view-questionnaire/view-questionnaire.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { GenerateSurveyComponent } from './generate-survey/generate-survey.component';
import { ViewSurveyComponent } from './view-survey/view-survey.component';
import { DataService } from './data.service';
import { AnswerSurveyComponent } from './answer-survey/answer-survey.component';
import { StartSurveyComponent } from './start-survey/start-survey.component';
import { QuestionsToAnswerComponent } from './questions-to-answer/questions-to-answer.component';
import {MatRadioModule} from '@angular/material/radio';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { EditQuestionnaireComponent } from './edit-questionnaire/edit-questionnaire.component';
import { EditSurveyComponent } from './edit-survey/edit-survey.component';
import { ConfirmSubmitComponent } from './confirm-submit/confirm-submit.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    StartseiteComponent,
    HeaderComponent,
    FragebogenErstellenComponent,
    FrageErstellenComponent,
    AnswerOptionErstellenComponent,
    FragePruefenComponent,
    DashboardFragebogenComponent,
    TransactionComponent,
    BefragungComponent,
    ConfirmDeleteComponent,
    SurveyResultComponent,
    ViewQuestionnaireComponent,
    GenerateSurveyComponent,
    ViewSurveyComponent,
    AnswerSurveyComponent,
    StartSurveyComponent,
    QuestionsToAnswerComponent,
    EditQuestionnaireComponent,
    EditSurveyComponent,
    ConfirmSubmitComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    FormsModule,
    MatDialogModule,
    MatSnackBarModule,
    MatStepperModule,
    MatCardModule,
    MatSelectModule,
    HttpClientModule,
    HttpClientJsonpModule,
    MatTooltipModule,
    MatExpansionModule,
    MatCheckboxModule,
    MatRadioModule,
    MatPaginatorModule,
    MatProgressBarModule,
    ReactiveFormsModule
  ],
  providers: [DataService, QuestionnaireService, QuestionService, AnswerOptionService, SurveyService, TransactionService, SurveyResultService],
  bootstrap: [AppComponent]
})
export class AppModule { }
