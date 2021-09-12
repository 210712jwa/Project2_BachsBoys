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

  getAPIInfo(city:string): Observable<any>{
    return this.hc.get<any>(`https://api.opentripmap.com/0.1/en/places/geoname?apikey=5ae2e3f221c38a28845f05b6c909371c86d58f679d475457a73d034c&name=${city}`, {
      withCredentials: false
    });

  }

  getAPIListings(lon: number, lat: number): Observable<any[]>{
    return this.hc.get<any[]>(`https://api.opentripmap.com/0.1/en/places/radius?apikey=5ae2e3f221c38a28845f05b6c909371c86d58f679d475457a73d034c&radius=1000&limit=5&offset=0&lon=${lon}&lat=${lat}&rate=2&format=json`, {
      withCredentials: false
    });
  }
  getListingInfo(xid:string): Observable<any[]>{
    return this.hc.get<any[]>(`https://api.opentripmap.com/0.1/en/places/xid/${xid}?apikey=5ae2e3f221c38a28845f05b6c909371c86d58f679d475457a73d034c`,)
  }

  checkIfAlreadyAdded(): Observable<Bucket>{
    return this.hc.get<Bucket>(`${environment.backendUrl}/Project2Testing/checkBucket`,{
      withCredentials: true
    });

  }

  addBucket(id: number): Observable<Bucket>{
    return this.hc.post<Bucket>(`${environment.backendUrl}/Project2Testing/addBucketToUser`,{
      'bucketId': id
    },{
      withCredentials: true
    });
  }
}
