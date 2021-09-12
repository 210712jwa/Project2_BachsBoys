import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './add-user/add-user.component';
import { BucketPageComponent } from './bucket-page/bucket-page.component';
import { LoginComponent } from './login/login.component';
import { UserPageComponent } from './user-page/user-page.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'user-page', component: UserPageComponent },
  { path: 'add-user', component: AddUserComponent },
  { path: 'bucket-page', component:BucketPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
