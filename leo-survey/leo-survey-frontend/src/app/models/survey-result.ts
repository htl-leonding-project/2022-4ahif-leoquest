import {Survey} from "./survey";

export class SurveyResult {

  public survey: Survey;
  public rightAnswers: number;
  public totalAnswers: number;

  public constructor(survey: Survey, rightAnswers: number, totalAnswers: number) {
    this.survey = survey;
    this.rightAnswers = rightAnswers;
    this.totalAnswers = totalAnswers;
  }
}
