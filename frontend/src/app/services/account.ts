import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private http = inject(HttpClient);

  getAccountDetails(accountNumber: string) {

    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.get(
      `http://localhost:8080/api/account/details/${accountNumber}`,
      { headers }
    );
  }

deposit(request: any) {

  const token = localStorage.getItem('token');

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  return this.http.post(
    'http://localhost:8080/api/account/deposit',
    request,
    {
      headers,
      responseType: 'text'
    }
  );
}

withdraw(request: any) {

  const token = localStorage.getItem('token');

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  return this.http.post(
    'http://localhost:8080/api/account/withdraw',
    request,
    {
      headers,
      responseType: 'text'
    }
  );
}

transfer(request: any) {

  const token = localStorage.getItem('token');

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  return this.http.post(
    'http://localhost:8080/api/account/transfer',
    request,
    {
      headers,
      responseType: 'text'
    }
  );
}

getTransactionHistory(accountNumber: string) {

  const token = localStorage.getItem('token');

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  return this.http.get(
    `http://localhost:8080/api/transaction/history/${accountNumber}`,
    { headers }
  );
}

getMyAccount() {

  const token = localStorage.getItem('token');

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  return this.http.get(
    'http://localhost:8080/api/account/my-account',
    { headers }
  );
}

getProfile() {

  const token = localStorage.getItem('token');

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  return this.http.get(
    'http://localhost:8080/api/account/profile',
    { headers }
  );
}

updateProfile(request: any) {

  const token = localStorage.getItem('token');

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  return this.http.put(
    'http://localhost:8080/api/account/profile',
    request,
    {
      headers,
      responseType: 'text'
    }
  );
}

changePassword(request: any) {

  const token = localStorage.getItem('token');

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  return this.http.post(
    'http://localhost:8080/api/account/change-password',
    request,
    {
      headers,
      responseType: 'text'
    }
  );
}

downloadStatement(
  fromDate: string,
  toDate: string
) {

  const token =
    localStorage.getItem('token');

  const headers =
    new HttpHeaders({

      Authorization:
        `Bearer ${token}`

    });

  return this.http.get(

    `http://localhost:8080/api/account/statement?fromDate=${fromDate}&toDate=${toDate}`,

    {
      headers,
      responseType: 'blob'
    }

  );
}

}