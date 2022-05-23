import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionsToAnswerComponent } from './questions-to-answer.component';

describe('QuestionsToAnswerComponent', () => {
  let component: QuestionsToAnswerComponent;
  let fixture: ComponentFixture<QuestionsToAnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionsToAnswerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionsToAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
