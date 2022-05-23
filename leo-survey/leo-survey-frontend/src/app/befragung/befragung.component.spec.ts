import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BefragungComponent } from './befragung.component';

describe('BefragungComponent', () => {
  let component: BefragungComponent;
  let fixture: ComponentFixture<BefragungComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BefragungComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BefragungComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
