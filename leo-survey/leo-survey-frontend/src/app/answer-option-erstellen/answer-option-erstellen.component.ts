import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AnswerOption } from '../models/answer-option';

@Component({
  selector: 'app-answer-option-erstellen',
  templateUrl: './answer-option-erstellen.component.html',
  styleUrls: ['./answer-option-erstellen.component.scss']
})
export class AnswerOptionErstellenComponent implements OnInit {
  @Input() answerOption!: AnswerOption;
  @Output() correctAnswer: EventEmitter<Number> = new EventEmitter<Number>();
  @Output() deleteAO: EventEmitter<Number> = new EventEmitter<Number>();
  
  constructor() {}

  ngOnInit(): void {
  }

  emitAnswer(sN : number){
    this.correctAnswer.emit(sN);
  }

  deleteAOption(sN: number){
    this.deleteAO.emit(sN);
  }

}
