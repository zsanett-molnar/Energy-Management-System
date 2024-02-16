import { TestBed } from '@angular/core/testing';

import { WebSocketShareService } from './web-socket-share.service';

describe('WebSocketShareService', () => {
  let service: WebSocketShareService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WebSocketShareService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
