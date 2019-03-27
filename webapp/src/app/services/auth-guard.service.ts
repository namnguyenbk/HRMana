import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot }
  from '@angular/router';
import { TdDialogService } from '@covalent/core/dialogs';

import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

import { CookieService } from 'ngx-cookie-service';
import { error } from 'util';

let currentTokenData: jwtData;
const loginApi: string = "http://localhost:9090/oauth/token";
const registrationApi: string = "http://localhost:9090/register";
const getStatusApi: string = "http://localhost:9090/getStatusUser";
const verifyApi: string = "http://localhost:9090/verify?verify_code=";

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

export interface commonRes {
  code : string;
  data : string;
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
    if (localStorage.getItem('access_token')) {
      // let refreshToken = {
      //   'grant_type': 'refresh_token',
      //   'refesh_token': this.cookieService.get('refresh_token'),
      // }

      // this.http.post<jwtData>(loginApi, refreshToken, httpOptions).subscribe(tokenData => {
      //   localStorage.setItem('acces_token', tokenData.access_token);
      //   localStorage.setItem('refresh_token', tokenData.refresh_token);
      // }, error => {
      //   return true;
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
      this.cookieTokenData(tokenData, usernameLg);
    }, error =>{
      if( error.message == "Unauthorized"){
        this.openAlert('Có lỗi khi đăng nhập!');

      }
    });

    this.http.post(getStatusApi, JSON.stringify({ "username": user.username }), httpOptions)
      .subscribe((res: any) => {
        if (res.status == 'VERIFY') {
          this.router.navigate(['']);
        } else {
          localStorage.clear();
          this.openPromptVerify('Mã xác nhận đã được gửi vào email của bạn. Nhập mã xác nhận vào đây!')
        }
      });
  }

  cookieTokenData(tokenData: jwtData, username: string) {
    currentTokenData = tokenData;
    localStorage.clear();
    localStorage.setItem('username', username);
    localStorage.setItem('access_token', tokenData.access_token);
    localStorage.setItem('refresh_token', tokenData.refresh_token);
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['login']);
  }

  registration(username: string, password: string, email: string, fname: string, lname: string) {

    let dataRegister = {
      'username': username,
      'password': password,
      'email': email,
      'firstname': fname,
      'lastname': lname,

    }

    this.http.post<any>(registrationApi, JSON.stringify(dataRegister), httpOptions)
      .subscribe(res => {
        if (res.code == "1002") {
          this.openAlert('Email đã tồn tại! Vui lòng sử dụng email khác.');
        }
        if (res.code == "1000") {
          localStorage.clear();
          this.openAlert('Kiểm tra mã mã xác nhận đươc gửi trong mail của bạn!');
          this.router.navigate(['login']);
        }
        if (res.code == "1103") {
          localStorage.clear();
          this.openAlert('Không thể gửi email!');
          this.router.navigate(['login']);
        }
      });;
  }

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

  openPromptVerify(message): void {
    this.dialogService.openPrompt({
      message: message,
      disableClose: true || false, // defaults to false
      // viewContainerRef: this.viewContainerRef, //OPTIONAL
      title: 'Xác nhận', //OPTIONAL, hides if not provided
      value: '', //OPTIONAL
      cancelButton: 'Huỷ', //OPTIONAL, defaults to 'CANCEL'
      acceptButton: 'Nhập', //OPTIONAL, defaults to 'ACCEPT'
      width: '500px', //OPTIONAL, defaults to 400px
    }).afterClosed().subscribe((value: string) => {
      if (value) {
        let httpOptions = {
          headers: new HttpHeaders({
            'Authorization': 'Bearer' + localStorage.getItem('access_token'),
          })
        };
        
        this.http.get(verifyApi + value.toString(), httpOptions ).subscribe( (res : commonRes) =>{
          if(res.code == '1000'){
            this.openAlert('Đăng kí thành công!');
            this.router.navigate(['']);
          }else{
            this.openAlert('Không thể xác thực!');
          }
        });
      } else {
        this.openAlert('Không thể xác thực!');
      }
    });
  }

}
