import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[numerico]',
  standalone: true
})
export class NumericoDirective {

  constructor() { }

  @HostListener('keyup', ['$event'])
  onKeyUp($event: any) {
    let valor = $event.target.value;
    // reg.ex. que remove tudo que não é número
    valor = valor.replace(/[\D]/g, '');
    $event.target.value = valor;
  }
//* importante: diretiva modifica o value do input
}
