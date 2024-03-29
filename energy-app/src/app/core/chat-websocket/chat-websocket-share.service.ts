import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatWebsocketShareService implements OnDestroy {
  
  private blogDataSubject = new BehaviorSubject<string>("");

  constructor() { }

   ngOnDestroy(): void {
    this.blogDataSubject .unsubscribe();
  }

  onNewValueReceive(msg: string) {        
      this.blogDataSubject .next(msg);
  }

  getNewValue(): Observable<string> {
      return this.blogDataSubject .asObservable();
  }

}
