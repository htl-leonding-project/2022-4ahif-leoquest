import { Question } from "./question";

export class AnswerOption {
    constructor(public id = 0, public text :string = "", public value = 0, public sequenceNumber = 0, public isCorrectAnswer = true, public question : Question = new Question()){}
}