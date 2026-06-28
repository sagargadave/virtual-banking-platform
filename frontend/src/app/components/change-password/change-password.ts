import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../../services/account';

@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './change-password.html',
  styleUrl: './change-password.css'
})
export class ChangePassword {

  currentPassword = '';
  newPassword = '';
  confirmPassword = '';

  constructor(
    private accountService: AccountService
  ) {}

  changePassword() {

    const request = {

      currentPassword: this.currentPassword,
      newPassword: this.newPassword,
      confirmPassword: this.confirmPassword

    };

    this.accountService
        .changePassword(request)
        .subscribe({

          next: (response) => {

            alert(response);

          },

          error: (err) => {

            console.log(err);

          }

        });

  }

}