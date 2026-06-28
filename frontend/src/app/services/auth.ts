import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);

register(request: any) {

  return this.http.post(
    'http://localhost:8080/api/auth/register',
    request,
    {
      responseType: 'text'
    }
  );
}

  login(data: any) {

    return this.http.post(
      'http://localhost:8080/api/auth/login',
      data,
      {
        responseType: 'text'
      }
    );
  }
}