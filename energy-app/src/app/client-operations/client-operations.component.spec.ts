import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientOperationsComponent } from './client-operations.component';

describe('ClientOperationsComponent', () => {
  let component: ClientOperationsComponent;
  let fixture: ComponentFixture<ClientOperationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientOperationsComponent]
    });
    fixture = TestBed.createComponent(ClientOperationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
