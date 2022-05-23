import { Component, Input, OnInit } from '@angular/core';
import { Administration } from '../models/administration';
import { QuestionType } from '../models/question-type';

@Component({
  selector: 'app-frage-pruefen',
  templateUrl: './frage-pruefen.component.html',
  styleUrls: ['./frage-pruefen.component.scss']
})
export class FragePruefenComponent implements OnInit {
  @Input() administration!: Administration;
  public questionTypeArray = new Array<String>("SingleChoice", "MultipleChoice", "YESORNO", "FREETEXT");
  public freetext = QuestionType.FREETEXT;

  constructor() { }

  ngOnInit(): void {
  }

}
