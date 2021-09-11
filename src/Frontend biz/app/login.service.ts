import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/model/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private hc: HttpClient) { }

  login(username: string, password: string): Observable<User> {
    return this.hc.post<User>(`${environment.backendUrl}/Project2/login`, {
      'username': username,
      'password': password
    }, {
      withCredentials: true
    });
  }

  checkIfLoggedIn(): Observable<User> {
    return this.hc.get<User>(`${environment.backendUrl}/Project2/currentUser`, {
      withCredentials: true
    });
  }

  logout() {
    return this.hc.post(`${environment.backendUrl}/Project2/logout`, {}, {
      withCredentials: true
    });
  }

}