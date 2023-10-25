import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacebookLoginCardComponent } from './facebook-login-card.component';

describe('FacebookLoginCardComponent', () => {
  let component: FacebookLoginCardComponent;
  let fixture: ComponentFixture<FacebookLoginCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacebookLoginCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacebookLoginCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
