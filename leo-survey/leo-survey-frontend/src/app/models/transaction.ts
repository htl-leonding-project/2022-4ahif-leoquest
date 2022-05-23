import { Questionnaire } from "./questionnaire";
import { Survey } from "./survey";

export class Transaction {
    constructor(public id = 0,
        public transactionCode : string = "",
        public isUsed : Boolean = false,
        public survey : Survey = new Survey()){
}
}
