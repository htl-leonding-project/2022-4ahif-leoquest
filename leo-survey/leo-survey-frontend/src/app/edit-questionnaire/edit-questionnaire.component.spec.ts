import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditQuestionnaireComponent } from './edit-questionnaire.component';

describe('EditQuestionnaireComponent', () => {
  let component: EditQuestionnaireComponent;
  let fixture: ComponentFixture<EditQuestionnaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditQuestionnaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditQuestionnaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
