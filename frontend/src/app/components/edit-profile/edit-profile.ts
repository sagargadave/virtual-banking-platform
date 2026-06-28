import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../../services/account';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './edit-profile.html',
  styleUrl: './edit-profile.css'
})
export class EditProfile implements OnInit {

  firstName = '';
  lastName = '';
  phone = '';
  address = '';

  constructor(
    private accountService: AccountService
  ) {}

  ngOnInit(): void {

    this.accountService
      .getProfile()
      .subscribe({

        next: (data: any) => {

          this.firstName = data.firstName;
          this.lastName = data.lastName;
          this.phone = data.phone;
          this.address = data.address;

        }

      });

  }

  updateProfile() {

    const request = {

      firstName: this.firstName,
      lastName: this.lastName,
      phone: this.phone,
      address: this.address

    };

    this.accountService
      .updateProfile(request)
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