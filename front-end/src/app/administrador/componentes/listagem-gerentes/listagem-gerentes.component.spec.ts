import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListagemGerentesComponent } from './listagem-gerentes.component';

describe('ListagemGerentesComponent', () => {
  let component: ListagemGerentesComponent;
  let fixture: ComponentFixture<ListagemGerentesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListagemGerentesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListagemGerentesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
