import { Answer } from "./answer";
import { AnswerOption } from "./answer-option";
import { Question } from "./question";

export class ChosenOption {
    constructor(public id = 0,
        public answerOption: AnswerOption = new AnswerOption(),
        public answer : Answer = new Answer(),
        public question : Question = new Question()){}
}
