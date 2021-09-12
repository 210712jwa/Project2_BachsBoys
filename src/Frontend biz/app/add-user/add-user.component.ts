import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/model/user';
import { AddUserService } from '../add-user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  username: string = '';
  password: string = '';
  firstname: string = '';
  lastname: string = '';

  showErrorMessage: boolean = false;
  errorMessage: string = '';

  constructor(private ls: AddUserService, private router: Router) { }

  ngOnInit(): void {
  }

  register(){
    this.ls.register(this.username, this.password, this.firstname, this.lastname).subscribe((data: User) => {
      // This callback function is invoked if logging in was successful (2XX status code)
      this.router.navigate(['']);
    },
    (err: HttpErrorResponse) => {
      // This callback is invoked if logging in was not successful (4XX or 5XX status code)
      this.showErrorMessage = true;
      this.errorMessage = err.error.message;
    });
  }

  goback(){
    this.router.navigate(['']);
  }


}
