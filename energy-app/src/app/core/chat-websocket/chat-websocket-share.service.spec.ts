import { TestBed } from '@angular/core/testing';

import { ChatWebsocketShareService } from './chat-websocket-share.service';

describe('ChatWebsocketShareService', () => {
  let service: ChatWebsocketShareService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChatWebsocketShareService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
