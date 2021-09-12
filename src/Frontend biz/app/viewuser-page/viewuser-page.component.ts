import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from 'src/model/user';
import { Bucket } from 'src/model/bucket';

@Component({
  selector: 'app-viewuser-page',
  templateUrl: './viewuser-page.component.html',
  styleUrls: ['./viewuser-page.component.css']
})
export class ViewuserPageComponent implements OnInit {

  viewedUser: any = null;

  citySearch: string = '';
  usernameSearch: string = '';

  showErrorMessage: boolean = false;
  errorMessage: string = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.checkSearchedUserCookie();
  }

  checkSearchedUserCookie(){
    this.userService.checkSearchedUserCookie().subscribe((data) => {
      this.viewedUser = data;

    });

  }

  addFriend(){

  }

  logout(){
    
  }



  searchUsername(){
    this.userService.searchUsername(this.usernameSearch).subscribe((data: User) => {

    },
    (err: HttpErrorResponse) =>{
      this.showErrorMessage = true;
      this.errorMessage = err.error.message;
    });
  }

  searchCity(){
    this.userService.searchCity(this.citySearch).subscribe((data: Bucket) => {

    });
  }

}
