import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlteracaoGerenteComponent } from './alteracao-gerente.component';

describe('AlteracaoGerenteComponent', () => {
  let component: AlteracaoGerenteComponent;
  let fixture: ComponentFixture<AlteracaoGerenteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlteracaoGerenteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlteracaoGerenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
