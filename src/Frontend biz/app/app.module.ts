import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ViewShipsComponent } from './view-ships/view-ships.component';
import { UserPageComponent } from './user-page/user-page.component';
import { AddUserComponent } from './add-user/add-user.component';
import { ViewuserPageComponent } from './viewuser-page/viewuser-page.component';
import { BucketPageComponent } from './bucket-page/bucket-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ViewShipsComponent,
    UserPageComponent,
    AddUserComponent,
    ViewuserPageComponent,
    BucketPageComponent 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
