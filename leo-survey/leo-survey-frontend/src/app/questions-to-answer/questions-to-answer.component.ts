import { Component, Input, OnInit } from '@angular/core';
import { AnswerHelp } from '../models/answer-help';
import { AnswerOption } from '../models/answer-option';
import { ChosenOption } from '../models/chosen-option';
import { QuestionType } from '../models/question-type';

@Component({
  selector: 'app-questions-to-answer',
  templateUrl: './questions-to-answer.component.html',
  styleUrls: ['./questions-to-answer.component.scss']
})
export class QuestionsToAnswerComponent implements OnInit {
  @Input() questionToAnswer!: AnswerHelp;
  public freetext = QuestionType.FREETEXT;
  public single = QuestionType.SINGLECHOICE;
  public muliple = QuestionType.MULTIPLECHOICE;
  public yesno = QuestionType.YESORNO;

  constructor() {
    
  }

  ngOnInit(): void {
  }

  setNewChosenValue(answero : AnswerOption){
    this.questionToAnswer.chosenOptionArray = [];
    this.questionToAnswer.chosenOptionArray.push(new ChosenOption(0,answero,this.questionToAnswer.answer,this.questionToAnswer.question));
  }

  checkIfAlreadySelected(ao :AnswerOption){
    return this.questionToAnswer.chosenOptionArray.length != 0 && this.questionToAnswer.chosenOptionArray[0].answerOption.id == ao.id;
  }

  addOrRemoveChosenValue(answero : AnswerOption){
    var c = new ChosenOption(0,answero,this.questionToAnswer.answer, this.questionToAnswer.question);
    var alreadyincluded = false;

    this.questionToAnswer.chosenOptionArray.forEach(e =>{
      if(e.answerOption.id == c.answerOption.id){
          alreadyincluded = true;
          this.questionToAnswer.chosenOptionArray.splice(this.questionToAnswer.chosenOptionArray.indexOf(e),1);
      }
    });

    if(!alreadyincluded){
      this.questionToAnswer.chosenOptionArray.push(c);
    }
  }

  checkIfAlreadySelectedMultiple(ao : Number){
    if(this.questionToAnswer.chosenOptionArray.length == 0){
      return false;
    }else{
      for(let i of this.questionToAnswer.chosenOptionArray) {
         if(i.answerOption.id == ao){
           return true;
         }
      }
      return false;
    }
    
  }

}
