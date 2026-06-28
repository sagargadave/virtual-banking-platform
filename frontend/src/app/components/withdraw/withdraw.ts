import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../../services/account';

@Component({
  selector: 'app-withdraw',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './withdraw.html',
  styleUrl: './withdraw.css'
})
export class Withdraw {

  accountNumber = '';
  amount = 0;

  constructor(
    private accountService: AccountService
  ) {}

  withdraw() {

    const request = {
      accountNumber: this.accountNumber,
      amount: this.amount
    };

    this.accountService
      .withdraw(request)
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