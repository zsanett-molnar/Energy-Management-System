import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociateDeviceComponent } from './associate-device.component';

describe('AssociateDeviceComponent', () => {
  let component: AssociateDeviceComponent;
  let fixture: ComponentFixture<AssociateDeviceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssociateDeviceComponent]
    });
    fixture = TestBed.createComponent(AssociateDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
