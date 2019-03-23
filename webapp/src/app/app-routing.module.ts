import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './user/login/login.component';
import {ManageListComponent} from './layouts/manage-list/manage-list.component';
import {AuthGuardService} from './services/auth-guard.service';
const ROUTES: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: ManageListComponent, canActivate: [AuthGuardService] },

];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(ROUTES),
  ],
  exports:[
    RouterModule,
  ],
  providers:[
    AuthGuardService,
  ]
})
export class AppRoutingModule {
  
 }
