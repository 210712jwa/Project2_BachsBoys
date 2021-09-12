import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bucket } from 'src/model/bucket';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BucketService {

  constructor(private hc: HttpClient) { }

  checkBucketCookie(): Observable<Bucket> {
    return this.hc.get<Bucket>(`${environment.backendUrl}/Project2Testing/getCurrentBucket`,{
      withCredentials: true
    });
  }
}
