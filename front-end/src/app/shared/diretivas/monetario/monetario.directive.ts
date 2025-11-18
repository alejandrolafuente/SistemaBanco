import { Directive, HostListener, forwardRef } from '@angular/core';
import { NG_VALUE_ACCESSOR, ControlValueAccessor } from '@angular/forms';

@Directive({
  selector: '[monetario]',
  standalone: true,
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => MonetarioDirective),
    multi: true
  }]
})
export class MonetarioDirective implements ControlValueAccessor {

  private onChange = (value: number | null) => { };
  private onTouched = () => { };

  @HostListener('focus', ['$event'])
  onFocus(event: any) {
    const input = event.target;
    if (!input.value || input.value === 'R$ 0,00') {
      input.value = '';
    }
  }

  @HostListener('blur', ['$event'])
  onBlur(event: any) {
    const input = event.target;
    if (!input.value) {
      input.value = 'R$ 0,00';
    }
    this.onTouched();
  }

  @HostListener('input', ['$event'])
  onInput(event: any) {
    const input = event.target;
    let valor = input.value.replace(/\D/g, '');

    // remove zeros da esquerda, mas mantem pelo menos um zero
    valor = valor.replace(/^0+/, '') || '0';

    if (!valor || valor === '0') {
      this.onChange(null);
      input.value = '';
      return;
    }

    const centavos = valor.slice(-2).padStart(2, '0');
    const reais = valor.slice(0, -2) || '0';
    input.value = `R$ ${reais},${centavos}`;

    const valorNumerico = parseFloat(valor) / 100;
    this.onChange(valorNumerico);
  }

  writeValue(value: number | null): void {
    // noo preenche com "R$ 0,00", fica a cargo do placeholder
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

}
