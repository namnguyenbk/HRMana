import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import {HeaderComponent} from '../layouts/header/header.component';
import {CommonModuleUi} from '../common-module-ui.module';

import { UserRoutingModule } from './user-routing.module';

import { AuthGuardService } from 'src/app/services/auth-guard.service';

@NgModule({
  declarations: [RegisterComponent, LoginComponent, HeaderComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    CommonModuleUi,
  ],
  bootstrap:[

  ],
  providers:[
    AuthGuardService,
  ]
})
export class UserModule { }
