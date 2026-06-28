import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../../services/account';

@Component({
  selector: 'app-account-statement',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './account-statement.html',
  styleUrl: './account-statement.css'
})
export class AccountStatement {

  fromDate = '';
  toDate = '';

  constructor(
    private accountService: AccountService
  ) {}

  downloadStatement() {

    if (!this.fromDate || !this.toDate) {

      alert('Please select both dates');

      return;
    }

    this.accountService
      .downloadStatement(
        this.fromDate,
        this.toDate
      )
      .subscribe({

        next: (blob: Blob) => {

          const url =
            window.URL.createObjectURL(blob);

          const a =
            document.createElement('a');

          a.href = url;

          a.download = 'statement.pdf';

          a.click();

          window.URL.revokeObjectURL(url);

        },

        error: (err) => {

          console.log(err);

          alert('Unable to download statement');

        }

      });
  }
}