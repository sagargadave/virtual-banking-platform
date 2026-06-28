import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  email = '';
  password = '';

  submitted = false;
  loginError = false;
  loading = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login() {

    this.submitted = true;
    this.loginError = false;

    if (!this.email.trim()) {
      return;
    }

    if (!this.password.trim()) {
      return;
    }

    const emailRegex =
      /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(this.email)) {
      return;
    }

    this.loading = true;

    const data = {

      email: this.email,

      password: this.password

    };

    this.authService
      .login(data)
      .subscribe({

        next: (token) => {

          this.loading = false;

          localStorage.setItem(
            'token',
            token
          );

          this.router.navigate(
            ['/dashboard']
          );

        },

        error: () => {

          this.loading = false;

          this.loginError = true;

        }

      });

  }

}