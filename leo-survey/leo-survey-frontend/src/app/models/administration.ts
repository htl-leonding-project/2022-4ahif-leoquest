import { AnswerOption } from "./answer-option"
import { Question } from "./question"

export class Administration {
    constructor(public question : Question = new Question(), public answerOptionArray : Array<AnswerOption>, public aocounter = 1){}
}
