import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AnswerOptionService } from '../http-service/answer-option.service';
import { QuestionService } from '../http-service/question.service';
import { QuestionnaireService } from '../http-service/questionnaire.service';
import { Administration } from '../models/administration';
import { AnswerOption } from '../models/answer-option';
import { Question } from '../models/question';
import { QuestionType } from '../models/question-type';
import { Questionnaire } from '../models/questionnaire';

@Component({
  selector: 'app-edit-questionnaire',
  templateUrl: './edit-questionnaire.component.html',
  styleUrls: ['./edit-questionnaire.component.scss']
})
export class EditQuestionnaireComponent implements OnInit {
  public questionnaire! :Questionnaire;
  public questions : Array<Question> = [];
  public answerOptions : Array<AnswerOption> = [];
  public adminarray = new Array<Administration>();
  public choseamountofquestions = false;
  public numberOfQuestions: number = 0;
  public userForm: FormGroup;
  public urlparts;

  constructor(public questionservice : QuestionService, public _snackBar: MatSnackBar, public router : Router, public qnService : QuestionnaireService, public aoService : AnswerOptionService) {
    this.userForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]),
      desc: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(250)])
    });
    this.urlparts = this.router.url;
    this.qnService.findByID(this.urlparts[this.urlparts.length-1]).subscribe((data : Questionnaire)=> {this.questionnaire = data});
  }

  ngOnInit(): void {
    this.aoService.getAllAnswerOptionsfromQuestionnaire(Number(this.urlparts[this.urlparts.length-1])).subscribe((data : any)=>{
      this.answerOptions = data;
      this.questionservice.getQuestionsByQuestionnaireId(Number(this.urlparts[this.urlparts.length-1])).subscribe((data : any) => {
        this.questions = data;
        this.questions.forEach(element => {
          this.choseamountofquestions = true;
          this.adminarray.push(new Administration(element, this.answerOptions.filter(ao => ao.question.id == element.id), this.answerOptions.filter(ao => ao.question.id == element.id).length));
        });
      });
    });
  }

  createQuestions(){
    if(this.numberOfQuestions < 1){
      this._snackBar.open("Die Anzahl der Fragen muss größer als 0 sein", "Verstanden");
    }else{
      for (var i = 1; i <= this.numberOfQuestions; i++) {
        this.adminarray.push(new Administration(new Question(0, "", i), []));
      }
      this.choseamountofquestions = true;
    }
  }

  deleteQuestioninArray(sN : number){
    //delete if in db
    if(this.adminarray[sN-1].question.id != 0){
      this.questionservice.deleteQuestion(this.adminarray[sN-1].question).subscribe();
    }
    
    this.adminarray.splice(sN -1,1);
    var sNcount = 1
    
    this.adminarray.forEach(element => {
      element.question.sequenceNumber = sNcount;
      sNcount++
    });

    if(this.adminarray.length == 0){
      this.choseamountofquestions = false;
    }
  }

  addQuestion(){
    this.choseamountofquestions = true;

    var elementcount = 1;
    this.adminarray.forEach(element => {
      elementcount++;
    });

   this.adminarray.push(new Administration(new Question(0, "", elementcount), []));
  }

  checkandsend(){//diffrent
    var i = 0;
    while(i < this.adminarray.length && this.adminarray[i].question.text != ""){
      i++;
    }

    if(this.questionnaire!.name == "" || this.questionnaire!.description == "" || i != this.adminarray.length){
      this._snackBar.open("Alle Pflichtfelder ausfüllen", "Verstanden");
    } else if(i == 0){
      this._snackBar.open("Der Fragebogen muss mindestens 1 Frage beinhalten", "Verstanden");
    }else{
      this.qnService.updateQn(this.questionnaire).subscribe();
      this.adminarray.forEach(element => {
        element.question.questionnaire = this.questionnaire;  
        
        if(element.question.id == 0){//insert Test: geht
          this.questionservice.insertQuestion(element.question).subscribe((data : Question) =>{
            element.question.id = data.id;
            for(var j = 0; j < element.answerOptionArray.length && element.question.questiontype != QuestionType.FREETEXT; j++){
              element.answerOptionArray[j].question = element.question;
              this.aoService.insertAnswerOption(element.answerOptionArray[j]).subscribe(data => {console.log("AnswerOption insert")});
            }//for j
          });//subscribe
        }else{//update
          this.questionservice.updateQuestion(element.question).subscribe((data : Question) =>{
            for(var j = 0; j < element.answerOptionArray.length && element.question.questiontype != QuestionType.FREETEXT; j++){
              element.answerOptionArray[j].question = element.question;
              if(element.answerOptionArray[j].id == 0){//insert
                this.aoService.insertAnswerOption(element.answerOptionArray[j]).subscribe(data => {console.log("AnswerOption insert")});  
              }else{//update
                this.aoService.updateAnswerOption(element.answerOptionArray[j]).subscribe(data => {console.log("AnswerOption update")});  
              }
             }//for j
          });
        }
      });    
      //this.router.navigate(['/dashboard']);
    }
  }

}
