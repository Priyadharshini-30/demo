import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstrnComponent } from './instrn.component';

describe('InstrnComponent', () => {
  let component: InstrnComponent;
  let fixture: ComponentFixture<InstrnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstrnComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstrnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
