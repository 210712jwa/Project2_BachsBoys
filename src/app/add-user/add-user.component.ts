import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  register(){

  }

  goback(){
    this.router.navigate(['login']);
  }


}
