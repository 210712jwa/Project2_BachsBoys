import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from 'src/model/user';
import { Bucket } from 'src/model/bucket';
import { Router } from '@angular/router';

@Component({
  selector: 'app-viewuser-page',
  templateUrl: './viewuser-page.component.html',
  styleUrls: ['./viewuser-page.component.css']
})
export class ViewuserPageComponent implements OnInit {

  viewedUser: any = null;
  isNotFriend: boolean = true;

  citySearch: string = '';
  usernameSearch: string = '';

  showErrorMessage: boolean = false;
  errorMessage: string = '';

  constructor(private router: Router,private userService: UserService) { }

  ngOnInit(): void {
    this.checkSearchedUserCookie();
    this.checkIfFriendAlready();
  }

  checkSearchedUserCookie(){
    this.userService.checkSearchedUserCookie().subscribe((data) => {
      this.viewedUser = data;

    });

  }
  checkIfFriendAlready(){
    this.userService.checkIfFriendAlready().subscribe((data)=>{
      if(data != null){
        this.isNotFriend = false;
      }

    });
  }

  addFriend(){
    this.userService.addFriend(this.viewedUser.id).subscribe((data)=>{
      this.router.navigate(['user-page']);
    });

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
