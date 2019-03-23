import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot }
  from '@angular/router';

let username = localStorage.getItem('username') || '';
let guest = (username) ? false : true;
let token = localStorage.getItem('token' || '');

@Injectable()
export class AuthGuardService {

  constructor(private router: Router) {
   }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this.isGuest()) {
      return true;
    } else {
      this.router.navigate(['/login'], {
        queryParams: {
          return: state.url
        }
      });
      return false;
    }
  }

  isGuest() {
    return guest;
  }

  getUser() {
    return username;
  }

  login(newUsername) {
    username = newUsername;
    guest = false;
    localStorage.setItem('username', username);
  }

  logout() {
    alert("Nam");
    username = '';
    token = '';
    guest = true;
    localStorage.setItem('username', '');
    localStorage.setItem('token', '');
    this.router.navigate(['login']);
  }

}
