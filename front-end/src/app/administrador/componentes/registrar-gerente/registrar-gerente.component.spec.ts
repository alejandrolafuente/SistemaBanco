import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarGerenteComponent } from './insercao-gerente.component';

describe('InsercaoGerenteComponent', () => {
  let component: RegistrarGerenteComponent;
  let fixture: ComponentFixture<RegistrarGerenteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrarGerenteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrarGerenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
