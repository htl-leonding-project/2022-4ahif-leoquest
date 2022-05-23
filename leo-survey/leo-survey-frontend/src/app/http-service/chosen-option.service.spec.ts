import { TestBed } from '@angular/core/testing';

import { ChosenOptionService } from './chosen-option.service';

describe('ChosenOptionService', () => {
  let service: ChosenOptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChosenOptionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
