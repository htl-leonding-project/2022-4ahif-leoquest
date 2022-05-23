import { Component, OnInit } from '@angular/core';
import { Interviewer } from '../models/interviewer';
import { Question } from '../models/question';
import { Questionnaire } from '../models/questionnaire';
import {MatSnackBar} from '@angular/material/snack-bar';
import { QuestionnaireService } from '../http-service/questionnaire.service';
import { QuestionService } from '../http-service/question.service';
import { AnswerOptionService } from '../http-service/answer-option.service';
import { Administration } from '../models/administration';
import { Router } from '@angular/router';
import { QuestionType } from '../models/question-type';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-fragebogen-erstellen',
  templateUrl: './fragebogen-erstellen.component.html',
  styleUrls: ['./fragebogen-erstellen.component.scss']
})
export class FragebogenErstellenComponent implements OnInit {
  public questionnaire: Questionnaire = new Questionnaire();
  public numberOfQuestions: number = 0;
  public surveyarray = new Array<Administration>();
  public choseamountofquestions = false;
  public userForm: FormGroup;
  public sendQn = false;

  constructor(public questionnaireservice : QuestionnaireService, public questionservice : QuestionService, public answerOptionservice : AnswerOptionService, public _snackBar: MatSnackBar, public router:Router) {
    this.userForm = new FormGroup({
        name: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]),
        desc: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(250)])
    });
  }

  ngOnInit(): void {
    this.questionnaire.interviewer = new Interviewer(1, "HTL Leonding");
  }

  createQuestions(){
    if(this.numberOfQuestions < 1){
      this._snackBar.open("Die Anzahl der Fragen muss größer als 0 sein", "Verstanden");
    }else{
      for (var i = 1; i <= this.numberOfQuestions; i++) {
        this.surveyarray.push(new Administration(new Question(0, "", i), []));
      }
      this.choseamountofquestions = true;
    }
  }

  deleteQuestioninArray(sN : number){
    this.surveyarray.splice(sN -1,1);
    var sNcount = 1
    
    this.surveyarray.forEach(element => {
      element.question.sequenceNumber = sNcount;
      sNcount++
    });

    if(this.surveyarray.length == 0){
      this.choseamountofquestions = false;
    }
  }

  addQuestion(){
    this.choseamountofquestions = true;

    var elementcount = 1;
    this.surveyarray.forEach(element => {
      elementcount++;
    });

   this.surveyarray.push(new Administration(new Question(0, "", elementcount), []));    
  }

  checkandsend(){
    this.sendQn = true;
    //check
    var i = 0;
    while(i < this.surveyarray.length && this.surveyarray[i].question.text != ""){
      i++;
    }

    if(this.questionnaire.name == "" || this.questionnaire.description == "" || i != this.surveyarray.length){
      this._snackBar.open("Alle Pflichtfelder ausfüllen", "Verstanden");
    } else if(i == 0){
      this._snackBar.open("Der Fragebogen muss mindestens 1 Frage beinhalten", "Verstanden");
    }else{
      //POST
      this.questionnaireservice.insertQuestionnaire(this.questionnaire).subscribe((data: Questionnaire) => {
        this.questionnaire = data;
        this.router.navigate(['/dashboard']);
       for(var i = 0; i < this.surveyarray.length; i++){          
          this.surveyarray[i].question.questionnaire = this.questionnaire;
          this.questionservice.insertQuestion(this.surveyarray[i].question).subscribe((data : Question) =>{
            this.surveyarray[data.sequenceNumber - 1].question.id = data.id;
            for(var j = 0; j < this.surveyarray[data.sequenceNumber - 1].answerOptionArray.length && this.surveyarray[data.sequenceNumber - 1].question.questiontype != QuestionType.FREETEXT; j++){
              this.surveyarray[data.sequenceNumber - 1].answerOptionArray[j].question = this.surveyarray[data.sequenceNumber - 1].question;
              this.answerOptionservice.insertAnswerOption(this.surveyarray[data.sequenceNumber - 1].answerOptionArray[j]).subscribe(data => {console.log("AnswerOption")});
            }//for j
          });//subscribe
        }//for i 
      });//s
      
    }//else
  }// function
}
