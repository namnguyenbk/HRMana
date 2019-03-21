import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {CommonModuleUi} from './common-module-ui.module';

import { AppComponent } from './app.component';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { ManageListComponent } from './layouts/manage-list/manage-list.component';
import { AppRoutingModule } from './app-routing.module';
import { UserModule} from './user/user.module';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    ManageListComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    UserModule,
    CommonModuleUi
  ],
  providers: [

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
