import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerOptionErstellenComponent } from './answer-option-erstellen.component';

describe('AnswerOptionErstellenComponent', () => {
  let component: AnswerOptionErstellenComponent;
  let fixture: ComponentFixture<AnswerOptionErstellenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnswerOptionErstellenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerOptionErstellenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
