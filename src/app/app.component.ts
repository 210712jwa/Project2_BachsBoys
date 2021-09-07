import { Component } from '@angular/core';
import { LoginService } from './login.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/model/User';
import { Location } from '@angular/common';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Project2-frontend';

  usernameInput: string = "";
  passwordInput: string = "";

  constructor(private loginService: LoginService, private location: Location, private route: ActivatedRoute,
    private router: Router){}

 login = (data: User) =>{
    this.router.navigate(['userpage'], { relativeTo: this.route });

 };

  loginData = {
    username : this.usernameInput,
    password : this.passwordInput
  }

  onLoginClick() {
     this.loginService.postLogin(this.loginData).subscribe(this.login);

  }

}
