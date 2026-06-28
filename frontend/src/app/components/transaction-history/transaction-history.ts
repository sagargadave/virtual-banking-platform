import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
import { AccountService } from '../../services/account';

@Component({
  selector: 'app-transaction-history',
  standalone: true,
  imports: [CommonModule,],
  templateUrl: './transaction-history.html',
  styleUrl: './transaction-history.css'
})
export class TransactionHistory implements OnInit {

  transactions: any[] = [];

constructor(
  private accountService: AccountService,
  private cdr: ChangeDetectorRef
) {}

ngOnInit(): void {

  this.accountService
    .getTransactionHistory('VBP1')
    .subscribe({
      next: (data: any) => {

        console.log("TRANSACTIONS:", data);

        this.transactions = data;

        this.cdr.detectChanges();

      },

      error: (err) => {

        console.log("ERROR:", err);

      }
    });
}
}