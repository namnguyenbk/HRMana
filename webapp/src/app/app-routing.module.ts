import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './user/login/login.component';
import {ManageListComponent} from './layouts/manage-list/manage-list.component';
const ROUTES: Routes = [
  { path: '', component: ManageListComponent },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(ROUTES),
  ],
  exports:[
    RouterModule,
  ]
})
export class AppRoutingModule {
  
 }
