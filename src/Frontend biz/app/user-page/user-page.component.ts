import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { User } from 'src/model/user';
import { UserService } from '../user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Bucket } from 'src/model/bucket';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {
  friends: User[] = [];

  citySearch: string = '';
  usernameSearch: string = '';

  showErrorMessage: boolean = false;
  errorMessage: string = '';
  
  constructor(private loginService: LoginService, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.getAllFriends();
  }
  searchUsername(){
    this.userService.searchUsername(this.usernameSearch).subscribe((data: User) => {
      this.router.navigate(['viewuser-page']);
    },
    (err: HttpErrorResponse) =>{
      this.showErrorMessage = true;
      this.errorMessage = err.error.message;
    }); 
  }

  searchCity(){
    this.userService.searchCity(this.citySearch).subscribe((data: Bucket) => {
      this.router.navigate(['bucket-page']);

    });
  }

  getAllFriends(){
    this.userService.getAllFriends().subscribe((data: User[]) =>{
      
    });
  }

  logout(){
    this.loginService.logout().subscribe((data) =>{
      this.router.navigate(['']);


    });
  }


}