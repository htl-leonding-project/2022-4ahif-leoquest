import { Interviewer } from "./interviewer";
import { Questionnaire } from "./questionnaire";

export class Survey {
    constructor(public id = 0,
                public interviewer : Interviewer = new Interviewer(1, "HTL Leonding"),
                public questionnaire : Questionnaire = new Questionnaire(),
                public title: string = "",
                public description : string = "",
                public surveyConducted : Boolean = false,
                public date : String = new Date().toISOString().substring(0,10)){
    }
}
