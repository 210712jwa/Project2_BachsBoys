import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/model/user';
import { Bucket } from 'src/model/bucket';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Friend } from 'src/model/friend';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private hc: HttpClient) { }

  getAllFriends(): Observable<any[]>{
    return this.hc.get<any[]>(`${environment.backendUrl}/Project2Testing/getAllFriends`,{
      withCredentials: true
    });
  }
  getAllFriendsForUser(id: number): Observable<any[]>{
    return this.hc.get<any[]>(`${environment.backendUrl}/Project2Testing/getAllFriendsForUser/${id}`,{
      withCredentials: true
    });
  }

  getAllBuckets(): Observable<Bucket[]>{
    return this.hc.get<Bucket[]>(`${environment.backendUrl}/Project2Testing/getUserBuckets`,{
      withCredentials: true
    })
  }
  getAllBucketsForUser(id: number): Observable<Bucket[]>{
    return this.hc.get<Bucket[]>(`${environment.backendUrl}/Project2Testing/getAllBucketsForUser/${id}`,{
      withCredentials: true
    })
  }
  
  addFriend(id:number):Observable<Friend>{
    return this.hc.post<Friend>(`${environment.backendUrl}/Project2Testing/addFriend`,{
      'friendId': id
    }, {
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

  checkSearchedUserCookie(): Observable<User>{
    return this.hc.get<User>(`${environment.backendUrl}/Project2Testing/searchedUser`,{
      withCredentials:true
    });
  }

  checkIfFriendAlready(): Observable<Friend>{
    return this.hc.get<Friend>(`${environment.backendUrl}/Project2Testing/checkFriend`,{
      withCredentials:true
    });
  }
}
