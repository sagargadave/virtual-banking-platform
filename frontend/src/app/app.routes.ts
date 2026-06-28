import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Dashboard } from './components/dashboard/dashboard';
import { Deposit } from './components/deposit/deposit';
import { Withdraw } from './components/withdraw/withdraw';
import { Transfer } from './components/transfer/transfer';
import { TransactionHistory } from './components/transaction-history/transaction-history';
import { Profile } from './components/profile/profile';
import { EditProfile } from './components/edit-profile/edit-profile';
import { ChangePassword } from './components/change-password/change-password';
import { Register } from './components/register/register';
import { authGuard } from './guards/auth-guard';
import { AccountStatement } from './components/account-statement/account-statement';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  {
    path: 'login',
    component: Login
  },

  {
    path: 'register',
    component: Register
  },

  {
    path: 'dashboard',
    component: Dashboard,
    canActivate: [authGuard]
  },

  {
    path: 'deposit',
    component: Deposit,
    canActivate: [authGuard]
  },

  {
    path: 'withdraw',
    component: Withdraw,
    canActivate: [authGuard]
  },

  {
    path: 'transfer',
    component: Transfer,
    canActivate: [authGuard]
  },

  {
    path: 'transaction-history',
    component: TransactionHistory,
    canActivate: [authGuard]
  },

  {
    path: 'profile',
    component: Profile,
    canActivate: [authGuard]
  },

  {
    path: 'edit-profile',
    component: EditProfile,
    canActivate: [authGuard]
  },

  {
    path: 'change-password',
    component: ChangePassword,
    canActivate: [authGuard]
  },

  {
  path: 'statement',
  component: AccountStatement,
  canActivate: [authGuard]
}

];