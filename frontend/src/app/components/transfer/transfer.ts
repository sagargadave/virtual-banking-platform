import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../../services/account';

@Component({
  selector: 'app-transfer',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './transfer.html',
  styleUrl: './transfer.css'
})
export class Transfer {

  fromAccountNumber = '';
  toAccountNumber = '';
  amount = 0;

  constructor(
    private accountService: AccountService
  ) {}

  transfer() {

const request = {
  fromAccount: this.fromAccountNumber,
  toAccount: this.toAccountNumber,
  amount: this.amount
};

    this.accountService
      .transfer(request)
      .subscribe({
        next: (response) => {

          alert(response);

        },

        error: (err) => {

          console.log(err);

          alert('Transfer Failed');

        }
      });
  }

}