import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {

  firstName = '';
  lastName = '';
  email = '';
  password = '';
  confirmPassword = '';
  phone = '';
  address = '';
  dateOfBirth = '';

  submitted = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  register() {

    this.submitted = true;

    if (!this.firstName.trim()) {
      return;
    }

    if (!this.lastName.trim()) {
      return;
    }

    if (!this.email.trim()) {
      return;
    }

    const emailRegex =
      /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(this.email)) {
      alert('Please enter a valid email');
      return;
    }

    if (this.password.length < 8) {
      return;
    }

    if (this.password !== this.confirmPassword) {
      return;
    }

    if (!this.phone.trim()) {
      return;
    }

    if (!this.address.trim()) {
      return;
    }

    if (!this.dateOfBirth) {
      return;
    }

    const request = {

      firstName: this.firstName,

      lastName: this.lastName,

      email: this.email,

      password: this.password,

      phone: this.phone,

      address: this.address,

      dateOfBirth: this.dateOfBirth

    };

    this.authService
      .register(request)
      .subscribe({

        next: (response) => {

          alert(response);

          this.router.navigate(['/login']);

        },

        error: (err) => {

          console.log(err);

          alert('Registration Failed');

        }

      });

  }

}