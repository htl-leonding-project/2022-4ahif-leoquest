import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FragePruefenComponent } from './frage-pruefen.component';

describe('FragePruefenComponent', () => {
  let component: FragePruefenComponent;
  let fixture: ComponentFixture<FragePruefenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FragePruefenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FragePruefenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
