
import { Component, HostBinding, ChangeDetectionStrategy } from '@angular/core';

import { TdMediaService } from '@covalent/core/media';
import { AuthGuardService } from '../../services/auth-guard.service';
import {UserInfoServiceService} from '../../services/user/user-info-service.service';
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
export class ManageListComponent {

  constructor(public media: TdMediaService, 
    private authService : AuthGuardService,
    private userInfoSerivce : UserInfoServiceService) { }

  isRoleAdmin() : boolean{
    return true;
  }

  logout() : void {
    this.authService.logout();
  }
}