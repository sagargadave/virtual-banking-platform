import {
  Component,
  OnInit,
  ChangeDetectorRef
} from '@angular/core';

import { CommonModule } from '@angular/common';
import { AccountService } from '../../services/account';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class Profile implements OnInit {

  profile: any = null;

  constructor(
    private accountService: AccountService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    this.accountService
      .getProfile()
      .subscribe({

        next: (data: any) => {

          this.profile = data;

          this.cdr.detectChanges();

        },

        error: (err) => {

          console.log(err);

        }

      });

  }

}