import { Component, OnInit } from '@angular/core';
import { Router, RoutesRecognized } from '@angular/router';
import { MatDialog } from '@angular/material';
import { AuthGuardService } from 'src/app/services/auth-guard.service';
// import {Au} from '../../services/auth-guard.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private routes: Router, private authService: AuthGuardService) { }
  username: string;
  password: string;

  ngOnInit() {
  }

  login(): void {
    this.authService.login(this.username, this.password);
  }
}
