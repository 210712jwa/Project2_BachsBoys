import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from 'src/model/user';
import { Bucket } from 'src/model/bucket';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-viewuser-page',
  templateUrl: './viewuser-page.component.html',
  styleUrls: ['./viewuser-page.component.css']
})
export class ViewuserPageComponent implements OnInit {

  viewedUser: any = null;
  isNotFriend: boolean = true;

  friends: any[] = [];
  buckets: Bucket[] = [];

  citySearch: string = '';
  usernameSearch: string = '';

  showErrorMessage: boolean = false;
  errorMessage: string = '';

  constructor(private loginService: LoginService, private router: Router,private userService: UserService) { }

  ngOnInit(): void {
    this.checkSearchedUserCookie();
    this.checkIfFriendAlready();
    
  }

  checkSearchedUserCookie(){
    this.userService.checkSearchedUserCookie().subscribe((data) => {
      this.viewedUser = data;
      this.getAllFriends();
      this.getAllBuckets();

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

  searchUsername(){
    this.userService.searchUsername(this.usernameSearch).subscribe((data: User) => {
      this.router.navigate(['viewuser-page']);
    },
    (err: HttpErrorResponse) =>{
      this.showErrorMessage = true;
      this.errorMessage = err.error.message;
    }); 
  }

  goToUser(username:string){
    this.userService.searchUsername(username).subscribe((data: User)=>{
      this.router.navigate(['viewuser-page']);
    })
  }

  goToBucket(city:string){
    this.userService.searchCity(city).subscribe((data: Bucket)=>{
      this.router.navigate(['bucket-page']);
    })
  }

  searchCity(){
    this.userService.searchCity(this.citySearch).subscribe((data: Bucket) => {
      this.router.navigate(['bucket-page']);

    });
  }

  getAllFriends(){
    this.userService.getAllFriendsForUser(this.viewedUser.id).subscribe((data: any[]) =>{
      this.friends = data;
      console.log(this.friends)
      
    });
  }

  getAllBuckets(){
    this.userService.getAllBucketsForUser(this.viewedUser.id).subscribe((data: Bucket[])=> {
      this.buckets = data;

    })
  }

  logout(){
    this.loginService.logout().subscribe((data) =>{
      this.router.navigate(['']);


    });
  }
}