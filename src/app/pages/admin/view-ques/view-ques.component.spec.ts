import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewQuesComponent } from './view-ques.component';

describe('ViewQuesComponent', () => {
  let component: ViewQuesComponent;
  let fixture: ComponentFixture<ViewQuesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewQuesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewQuesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
