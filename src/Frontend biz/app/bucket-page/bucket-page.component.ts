import { Component, OnInit } from '@angular/core';
import { BucketService } from '../bucket.service';
import { Bucket } from 'src/model/bucket';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bucket-page',
  templateUrl: './bucket-page.component.html',
  styleUrls: ['./bucket-page.component.css']
})
export class BucketPageComponent implements OnInit {
  bucket: any = null;
  city: string = "";
  country: string = "";
  isNotAdded: boolean = true;

  map: any = null;

  apiListings: any[] = [];
  listingInfo: any[] = [];


  constructor(private bs: BucketService, private router: Router) { }

  ngOnInit(): void {
    console.log("here");
    this.checkBucketCookie();
    this.checkIfAlreadyAdded();
    console.log("here2");

  }


  displayListings(){
    this.bs.getAPIInfo(this.bucket.city).subscribe((data) => {
      this.bs.getAPIListings(data.lon , data.lat).subscribe((data) => {
        this.apiListings = data;
        for( let i = 0; i < 5; i++){
          this.bs.getListingInfo(this.apiListings[i].xid).subscribe((data) => {
            this.listingInfo.push(data);
          })
        }
        console.log(this.listingInfo)

      })

    })

  }

  checkIfAlreadyAdded(){
    this.bs.checkIfAlreadyAdded().subscribe((data) => {
      if(data != null){
        this.isNotAdded= false;
      } else {
        this.isNotAdded= true;
      }

    })
  }
  addBucket(){
    this.bs.addBucket(this.bucket.id).subscribe((data) =>{
      this.router.navigate(['user-page']);

    })
  }

  checkBucketCookie() {
    this.bs.checkBucketCookie().subscribe((data) => {
      this.bucket = data;
      this.displayListings();
      // if we have a 2xx status code, it will invoke this callback function
      // therefore, we would know that we are logged in, because currentuser returns 200
      // if we are indeed logged in already
    });

  }

}
