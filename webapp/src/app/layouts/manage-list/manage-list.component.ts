
import { Component, HostBinding, ChangeDetectionStrategy, OnInit, OnChanges } from '@angular/core';

import { TdMediaService } from '@covalent/core/media';
import { AuthGuardService } from '../../services/auth-guard.service';
import {UserInfoServiceService} from '../../services/user/user-info-service.service';

import { UserInfo } from '../../interface/user-interface';

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'layouts-manage-list',
  styleUrls: ['./manage-list.component.css'],
  templateUrl: './manage-list.component.html',
  providers: [
    TdMediaService,
    AuthGuardService,
  ]
})
export class ManageListComponent implements OnInit {
  userInfo : UserInfo;
  constructor(public media: TdMediaService, 
    private authService : AuthGuardService,
    private userInfoSerivce : UserInfoServiceService) {
      
      this.userInfoSerivce.getUserInfo().subscribe( (resData : UserInfo ) =>{
        this.userInfo = resData;
      });
    }

    ngOnInit(){
    }
  
  isRoleAdmin() : boolean{
    if( this.userInfo.role && this.userInfo.role == '1'){
      return true;
    }
    return false;
  }

  logout() : void {
    this.authService.logout();
  }
}