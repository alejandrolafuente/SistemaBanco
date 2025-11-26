import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {
  handleHttpError(error: HttpErrorResponse): string {
    //padrao customizado - exceptions no server
    if (error.error && error.error.mensagem) return error.error.mensagem;
    // padrao java
    if (error.error && error.error.message) return error.error.message;
    // strings simples
    if (typeof error.error === 'string') return error.error;
    return 'Erro inesperado';
  }
}
