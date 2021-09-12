import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from 'src/model/user';

@Injectable({
  providedIn: 'root'
})
export class AddUserService {

  constructor(private hc: HttpClient) { }


  register(username: string, password: string, firstname: string, lastname: string): Observable<User> {
    return this.hc.post<User>(`${environment.backendUrl}/Project2/addUser`, {
      'username': username,
      'password': password,
      'firstName': firstname,
      'lastName': lastname
    }, {
      withCredentials: true
    });
  }

}
