import { Question } from "./question";
import { Transaction } from "./transaction";

export class Answer {
    constructor(public id = 0,
                public question : Question = new Question(),
                public answerText : string = "",
                public transaction : Transaction = new Transaction()){}
}
