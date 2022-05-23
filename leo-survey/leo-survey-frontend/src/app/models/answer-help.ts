import { Answer } from "./answer";
import { AnswerOption } from "./answer-option";
import { ChosenOption } from "./chosen-option";
import { Question } from "./question";

export class AnswerHelp {
    constructor(public question : Question = new Question(), public answerOptionArray : Array<AnswerOption> = [], public answer : Answer = new Answer(), public chosenOptionArray : Array<ChosenOption> = []){}
}
