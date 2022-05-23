import { AnswerOption } from "./answer-option";
import { QuestionType } from "./question-type";
import { Questionnaire } from "./questionnaire";

export class Question {
    constructor(public id =  0, public text : string = "", public sequenceNumber = 0, public questiontype : QuestionType = QuestionType.FREETEXT, public img: Blob = new Blob(), public questionnaire : Questionnaire = new Questionnaire()){}
}
