import { Interviewer } from "./interviewer";

export class Questionnaire {
    constructor(public id = 0, public name :string = "", public description : string = "", public isPublic: Boolean = false, public interviewer : Interviewer = new Interviewer(1, "HTL Leonding")){}
}