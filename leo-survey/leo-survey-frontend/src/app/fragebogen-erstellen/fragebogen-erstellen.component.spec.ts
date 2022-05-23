import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FragebogenErstellenComponent } from './fragebogen-erstellen.component';

describe('FragebogenErstellenComponent', () => {
  let component: FragebogenErstellenComponent;
  let fixture: ComponentFixture<FragebogenErstellenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FragebogenErstellenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FragebogenErstellenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
