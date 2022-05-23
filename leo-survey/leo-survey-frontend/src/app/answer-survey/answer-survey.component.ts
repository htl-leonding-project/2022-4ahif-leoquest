import { Component, EventEmitter, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { ConfirmSubmitComponent } from '../confirm-submit/confirm-submit.component';
import { DataService } from '../data.service';
import { AnswerOptionService } from '../http-service/answer-option.service';
import { AnswerService } from '../http-service/answer.service';
import { ChosenOptionService } from '../http-service/chosen-option.service';
import { QuestionService } from '../http-service/question.service';
import { SurveyService } from '../http-service/survey-service.service';
import { TransactionService } from '../http-service/transaction-service.service';
import { Answer } from '../models/answer';
import { AnswerHelp } from '../models/answer-help';
import { AnswerOption } from '../models/answer-option';
import { Question } from '../models/question';
import { QuestionType } from '../models/question-type';
import { Questionnaire } from '../models/questionnaire';
import { StartSurveyComponent } from '../start-survey/start-survey.component';

@Component({
  selector: 'app-answer-survey',
  templateUrl: './answer-survey.component.html',
  styleUrls: ['./answer-survey.component.scss']
})
export class AnswerSurveyComponent implements OnInit {
  public showAnswers : Boolean = false;
  public questions : Array<Question> = [];
  public answerOptions : Array<AnswerOption> = [];
  public questionnaire! : Questionnaire;
  public questionsToAnswer : Array<AnswerHelp> = [];
  public pageSizeOptions: number[] = [2, 3, 5, 10];
  public indexArray : Array<number> = [];
  progressbarValue: number = 0;

  constructor( public submitconfirmation: MatDialog, public router:Router,public answerService : AnswerService, public chosenService : ChosenOptionService ,public startAnswering: MatDialog,public transactionService : TransactionService, public surveyService : SurveyService, public dataS : DataService, public questionService : QuestionService, public aoService: AnswerOptionService) { }

  ngOnInit(): void {
    const dialogRef = this.startAnswering.open(StartSurveyComponent,{width:"40%"});

    dialogRef.afterClosed().subscribe(result => {
      if((typeof result) ==  "undefined"){
        this.router.navigate(['/startseite']);
      }

      if((typeof result) ==  "string" && result == "Start"){
        //einkommentiern
       //this.dataS.transaction!.isUsed = true;
        this.transactionService.updateTransaction(this.dataS.transaction!).subscribe();

        this.surveyService.getQuestionnaireWithSurveyId(this.dataS.survey!.id).subscribe((data : Questionnaire)=>{

          this.questionnaire = data;
          this.questionService.getQuestionsByQuestionnaireId(this.questionnaire.id).subscribe((data : Question[])=>{
            this.questions = data;
            this.aoService.getAllAnswerOptionsfromQuestionnaire(this.questionnaire.id).subscribe((data : any)=>{
              this.answerOptions = data;
              this.questions.forEach(element => {
                this.questionsToAnswer.push(new AnswerHelp(element, this.answerOptions.filter(ao => ao.question.id == element.id), new Answer(0,element,"",this.dataS.transaction), []));
              });
              this.fillArray(0, this.pageSizeOptions[1]);
              this.showAnswers = true;
            });
          });
        });
      }
    });
  }

  fillArray(start : number, end : number){
    this.indexArray = [];
    for (let index = start; index < end; index++) {
      if(index < this.questionsToAnswer.length){
        this.indexArray.push(index);
      }
    }
  }

  recalc(pageEvent: PageEvent){
    this.fillArray(pageEvent.pageSize * pageEvent.pageIndex, pageEvent.pageSize * (pageEvent.pageIndex + 1));

    this.progressbarValue = Math.floor((this.openQuestions() / this.questionsToAnswer.length ) * 100);
  }

  openQuestions(): number{
    var open = 0;

    this.questionsToAnswer.forEach(element => {
      if(element.question.questiontype == QuestionType.FREETEXT && element.answer.answerText == ""){
        open++;
      }

      if(element.question.questiontype != QuestionType.FREETEXT && element.chosenOptionArray.length == 0){
        open++;
      }
    });

    return open;
  }

  submitAnswers(){
    const dialogRef = this.submitconfirmation.open(ConfirmSubmitComponent, {width:"30%",
    data: {
      notanswered : this.openQuestions(),
      questions : this.questionsToAnswer.length
    }});

    dialogRef.afterClosed().subscribe(result => {
      if((typeof result) ==  "string" && result == "Submit"){
        this.questionsToAnswer.forEach(element => {
          //POST
          this.answerService.insertAnswer(element.answer).subscribe((data: Answer) => {
            element.answer = data;
            for(var i = 0; i < element.chosenOptionArray.length; i++){
              element.chosenOptionArray[i].answer = element.answer;
              this.chosenService.insertChosenOption(element.chosenOptionArray[i]).subscribe();
            }

          });
          this.router.navigate(['/startseite']);
        });
      }
    });
  }
}
