import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../../services/account';

@Component({
  selector: 'app-deposit',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './deposit.html',
  styleUrl: './deposit.css'
})
export class Deposit {

  accountNumber = '';
  amount = 0;

  constructor(
    private accountService: AccountService
  ) {}

  deposit() {

    const request = {
      accountNumber: this.accountNumber,
      amount: this.amount
    };

    this.accountService.deposit(request)
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