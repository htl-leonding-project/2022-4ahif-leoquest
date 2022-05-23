import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StartSurveyComponent } from './start-survey.component';

describe('StartSurveyComponent', () => {
  let component: StartSurveyComponent;
  let fixture: ComponentFixture<StartSurveyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StartSurveyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StartSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
