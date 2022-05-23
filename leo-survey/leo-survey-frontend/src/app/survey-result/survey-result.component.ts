import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { SurveyResult } from "../models/survey-result";
import {SurveyResultService} from "../http-service/survey-result.service";
import { EChartsOption } from "echarts";

@Component({
  selector: 'app-survey-result',
  templateUrl: 'survey-result.component.html',
  styleUrls: ['./survey-result.component.scss']
})
export class SurveyResultComponent implements OnInit {

  private surveyId: number = -1;
  public surveyResult?: SurveyResult;

  public chartOption?: EChartsOption;

  public constructor(private activatedRoute: ActivatedRoute,
                     private surveyResultService: SurveyResultService) {
  }

  public async ngOnInit(): Promise<void> {
    this.surveyId = this.activatedRoute.snapshot.params.surveyIdParam;

    this.surveyResult = await this.surveyResultService.getSurveyResult(this.surveyId);

    this.chartOption = {
      xAxis: {
        type: 'category',
        data: ['10%', '2', '3', '4', 'Fri', 'Sat', 'Sun'],
      },
      yAxis: {
        type: 'value',
      },
      series: [
        {
          data: [2000, 932, 901, 934, 1290, 1330, 1320],
          type: 'line',
        },
      ],
    };
  }
}
