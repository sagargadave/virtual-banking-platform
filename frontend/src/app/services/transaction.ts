import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private http = inject(HttpClient);

  getMiniStatement(accountNumber: string) {

    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.get(
      `http://localhost:8080/api/transaction/mini-statement/${accountNumber}`,
      { headers }
    );
  }

}