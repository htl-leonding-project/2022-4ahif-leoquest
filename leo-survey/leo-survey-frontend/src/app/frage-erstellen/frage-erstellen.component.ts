import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AnswerOptionService } from '../http-service/answer-option.service';
import { Administration } from '../models/administration';
import { AnswerOption } from '../models/answer-option';
import { Question } from '../models/question';
import { QuestionType } from '../models/question-type';

@Component({
  selector: 'app-frage-erstellen',
  templateUrl: './frage-erstellen.component.html',
  styleUrls: ['./frage-erstellen.component.scss']
})
export class FrageErstellenComponent implements OnInit {
  @Input() administration!: Administration;
  @Output() deleteQ : EventEmitter<Number> = new EventEmitter<Number>();

  public questiontypes = Object.values(QuestionType).filter(value => typeof value === 'string');
  public freetext = QuestionType.FREETEXT;
  public yesno = QuestionType.YESORNO;
 
  constructor(public aoService : AnswerOptionService) { }

  ngOnInit(): void {}

  addAnswerOption(){
    this.administration.answerOptionArray.push(new AnswerOption(0, "", this.administration.aocounter, this.administration.aocounter, false, this.administration.question));
    this.administration.aocounter++;
  }

  deleteQuestion(sN: number){
    this.deleteQ.emit(sN);
  }

  addAnswerOptionforYN(){
    this.administration.answerOptionArray = [];
    this.administration.aocounter  = 1;
    if(this.administration.question.questiontype == this.yesno){
      this.administration.answerOptionArray.push(new AnswerOption(0, "Yes", this.administration.aocounter, this.administration.aocounter, false, this.administration.question));
      this.administration.aocounter++;
      this.administration.answerOptionArray.push(new AnswerOption(0, "No", this.administration.aocounter, this.administration.aocounter, false, this.administration.question));
    }//if
  }

  deleteAOinArray(sN : number){
    if(this.administration.answerOptionArray[sN-1].id != 0){
      this.aoService.deleteAO(this.administration.answerOptionArray[sN-1].id).subscribe();
    }

    this.administration.answerOptionArray.splice(sN- 1, 1);
    var count = 1;
    this.administration.answerOptionArray.forEach(element => {
      element.sequenceNumber = count;
      count++;
    });
  }

  checkAOCount(sN: number){
    if(QuestionType.SINGLECHOICE == this.administration.question.questiontype){
      this.administration.answerOptionArray.forEach(element => {   
        element.isCorrectAnswer = false;
      });
     
    } 
  }

  onChange(event: any) {//not tested
    console.log(event.target.files[0]);
    this.administration.question.img = event.target.files[0];   
  }

}
