import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/model/user';
import { Bucket } from 'src/model/bucket';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private hc: HttpClient) { }

  getAllFriends(): Observable<User[]>{
    return this.hc.get<User[]>(`${environment.backendUrl}/Project2Testing/getAllFriends`,{
      withCredentials: true
    });
  }

  searchUsername(username:string): Observable<User>{
    return this.hc.get<User>(`${environment.backendUrl}/Project2Testing/getUserByUsername?username=${username}`,{
      withCredentials: true
    });
  }
  searchCity(city:string): Observable<Bucket>{
    return this.hc.get<Bucket>(`${environment.backendUrl}/Project2Testing/getBucketByCity?city=${city}`,{
      withCredentials: true
    });
  }
}
