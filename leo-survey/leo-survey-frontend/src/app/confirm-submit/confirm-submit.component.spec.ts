import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmSubmitComponent } from './confirm-submit.component';

describe('ConfirmSubmitComponent', () => {
  let component: ConfirmSubmitComponent;
  let fixture: ComponentFixture<ConfirmSubmitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmSubmitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmSubmitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
