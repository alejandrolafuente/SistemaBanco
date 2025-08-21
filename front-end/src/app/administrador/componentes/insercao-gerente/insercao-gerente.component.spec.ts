import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsercaoGerenteComponent } from './insercao-gerente.component';

describe('InsercaoGerenteComponent', () => {
  let component: InsercaoGerenteComponent;
  let fixture: ComponentFixture<InsercaoGerenteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InsercaoGerenteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InsercaoGerenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
