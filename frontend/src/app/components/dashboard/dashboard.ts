  import { Component, OnInit, ChangeDetectorRef  } from '@angular/core';
  import { AccountService } from '../../services/account';
  import { CommonModule } from '@angular/common';
  import { RouterLink } from '@angular/router';
  import { Router } from '@angular/router';
  import { TransactionService } from '../../services/transaction';

  @Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [CommonModule, RouterLink,],
    templateUrl: './dashboard.html',
    styleUrl: './dashboard.css'
  })

export class Dashboard implements OnInit {

  account: any = null;
  transactions: any[] = [];
  profile: any = null;

  constructor(
    private accountService: AccountService,
    private transactionService: TransactionService,
    private cdr: ChangeDetectorRef,
    private router: Router
  ) {}

  ngOnInit(): void {

    // get account
    this.accountService
      .getMyAccount()
      .subscribe({

        next: (data: any) => {

          console.log("ACCOUNT DATA:", data);

          this.account = data;

          this.transactionService
            .getMiniStatement(this.account.accountNumber)
            .subscribe({

              next: (res: any) => {

                console.log("TRANSACTIONS:", res);

                this.transactions = res;

                this.cdr.detectChanges();

              },

              error: (err) => {

                console.log("TRANSACTION ERROR:", err);

              }

            });

        },

        error: (err) => {

          console.log("ACCOUNT ERROR:", err);

        }

      });

    // get profile
    this.accountService
      .getProfile()
      .subscribe({

        next: (data: any) => {

          console.log("PROFILE DATA:", data);

          this.profile = data;

        },

        error: (err) => {

          console.log("PROFILE ERROR:", err);

        }

      });

  }

  logout() {

    localStorage.removeItem('token');

    this.router.navigate(['/login']);

  }

}