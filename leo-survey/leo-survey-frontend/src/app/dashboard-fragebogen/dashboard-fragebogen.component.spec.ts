import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardFragebogenComponent } from './dashboard-fragebogen.component';

describe('DashboardFragebogenComponent', () => {
  let component: DashboardFragebogenComponent;
  let fixture: ComponentFixture<DashboardFragebogenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardFragebogenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardFragebogenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
