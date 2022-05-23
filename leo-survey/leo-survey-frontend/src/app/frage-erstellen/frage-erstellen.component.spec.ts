import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FrageErstellenComponent } from './frage-erstellen.component';

describe('FrageErstellenComponent', () => {
  let component: FrageErstellenComponent;
  let fixture: ComponentFixture<FrageErstellenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FrageErstellenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FrageErstellenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
