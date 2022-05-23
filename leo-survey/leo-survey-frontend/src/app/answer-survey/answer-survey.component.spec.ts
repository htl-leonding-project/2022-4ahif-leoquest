import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerSurveyComponent } from './answer-survey.component';

describe('AnswerSurveyComponent', () => {
  let component: AnswerSurveyComponent;
  let fixture: ComponentFixture<AnswerSurveyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnswerSurveyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
