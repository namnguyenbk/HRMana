import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot }
  from '@angular/router';
import { TdDialogService } from '@covalent/core/dialogs';

import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

import { CookieService } from 'ngx-cookie-service';

let currentTokenData: jwtData;
const loginApi: string = "http://localhost:9090/oauth/token";
const registrationApi: string = "http://localhost:9090/register";
const getStatusApi: string = "http://localhost:9090/getStatusUser";

const httpOptionsAuth = {
  headers: new HttpHeaders({
    'Content-Type': 'application/x-www-form-urlencoded',
    'Authorization': 'Basic bG9naW5zZXJ2aWNlOmRldmdsYW4tc2VjcmV0'
  })
};

let httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

export interface UserLogin {
  username: string;
  password: string;
  grant_type: string;
}

export interface jwtData {
  access_token: string;
  token_type: string;
  refresh_token: string;
  expires_in: string;
  scope: string;
  jti: string;
}

@Injectable()
export class AuthGuardService {

  constructor(private router: Router, private http: HttpClient, private cookieService: CookieService,
    private dialogService: TdDialogService) {
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
    if (this.cookieService.get('access_token')) {
      // let refreshToken = {
      //   'grant_type' : 'refresh_token',
      //   'refesh_token' : this.cookieService.get('refresh_token'),
      // }

      // this.http.post<jwtData>( loginApi,refreshToken , httpOptions).subscribe( tokenData =>{
      //   this.cookieTokenData(tokenData);
      // });
      return false;
    } else {
      return true;
    }
  }

  getUser() {
    // return username;
  }

  login(usernameLg: string, passwordLg: string) {
    let user: UserLogin = {
      'grant_type': 'password',
      'username': usernameLg,
      'password': passwordLg,
    };
    const userLoginDataEncodedUrl = Object.keys(user).filter(k => user.hasOwnProperty(k)).map(
      k => encodeURIComponent(k) + '=' + encodeURIComponent(user[k])).join('&');
    this.http.post<jwtData>(loginApi, userLoginDataEncodedUrl, httpOptionsAuth).subscribe(tokenData => {
      this.cookieTokenData(tokenData);
    });
    
   this.http.post(getStatusApi, JSON.stringify({"username": user.username}), httpOptions )
   .subscribe( (res : any) =>{
     if( res.status == 'VERIFY'){
      this.router.navigate(['']);
     }else{
      this.cookieService.deleteAll();
      // alert('Tài khoản của bạn đang chờ duyệt!');
      this.openAlert('Tài khoản của bạn đang chờ duyệt!');
      this.router.navigate(['login']);
     }
   });
  }

  cookieTokenData(tokenData: jwtData) {
    currentTokenData = tokenData;
      this.cookieService.deleteAll();
      this.cookieService.set('access_token', tokenData.access_token);
      this.cookieService.set('refresh_token', tokenData.refresh_token);
  }

  logout() {
    this.cookieService.deleteAll();
    this.router.navigate(['login']);
  }

  registration(username: string, password: string, email: string, fname: string, lname: string) {
    
    let dataRegister = {
      'username': username,
      'password': password,
      'email': email,
      'fname': fname,
      'lname': lname,

    }

    this.http.post<any>(registrationApi, JSON.stringify(dataRegister), httpOptions)
      .subscribe(res => {
        if (res.code == "1002") {
          this.openAlert('Email đã tồn tại! Vui lòng sử dụng email khác.');
        }
        if (res.code == "1000") {
          this.cookieService.deleteAll();
          this.openAlert('Đăng kí thành công! Tài khoản của bạn đang chờ duyệt!');
          this.router.navigate(['login']);
        }
      });;
  }

  parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
  };

  openAlert(message): void {
    this.dialogService.openAlert({
      message: message,
      disableClose: true || false, // defaults to false
      // viewContainerRef: ViewContainerRef, //OPTIONAL
      title: 'Thông báo!', //OPTIONAL, hides if not provided
      closeButton: 'Đóng', //OPTIONAL, defaults to 'CLOSE'
      width: '450px', //OPTIONAL, defaults to 400px
    });
  }

}
