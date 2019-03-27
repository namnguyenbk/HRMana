import { Component, OnInit } from '@angular/core';
import {AuthGuardService} from '../../services/auth-guard.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  fname : string;
  lname : string;
  username: string;
  email : string;
  password : string;
  passwordConf : string;
  gender : string;
  address : string;
  dateBirth : Date;

  constructor( private authService : AuthGuardService) { }

  ngOnInit() {
  }

  register() : void{
    if(this.password != this.passwordConf){
      this.authService.openAlert('Hai mật khẩu không trùng nhau!');
    }else{
      this.authService.registration(this.username, this.password, this.email, this.fname, this.lname)
      
    }
  }

}
