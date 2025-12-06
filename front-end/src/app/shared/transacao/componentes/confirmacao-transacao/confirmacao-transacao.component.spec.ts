import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmacaoTransacaoComponent } from './confirmacao-transacao.component';

describe('ConfirmacaoTransacaoComponent', () => {
  let component: ConfirmacaoTransacaoComponent;
  let fixture: ComponentFixture<ConfirmacaoTransacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfirmacaoTransacaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfirmacaoTransacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
