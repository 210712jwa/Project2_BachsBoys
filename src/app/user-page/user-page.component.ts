import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {


  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  logout(){
    this.loginService.logout().subscribe((data) =>{
      this.router.navigate(['']);


    });
  }


}