import { Component, OnInit } from '@angular/core';
import { BucketService } from '../bucket.service';
import { Bucket } from 'src/model/bucket';

@Component({
  selector: 'app-bucket-page',
  templateUrl: './bucket-page.component.html',
  styleUrls: ['./bucket-page.component.css']
})
export class BucketPageComponent implements OnInit {
  bucket: any = null;
  city: string = "";
  country: string = "";


  constructor(private bs: BucketService) { }

  ngOnInit(): void {
    this.checkBucketCookie();
    this.city = this.bucket.city;
    this.country = this.bucket.country;

  }

  checkBucketCookie() {
    this.bs.checkBucketCookie().subscribe((data) => {
      this.bucket = data;
      // if we have a 2xx status code, it will invoke this callback function
      // therefore, we would know that we are logged in, because currentuser returns 200
      // if we are indeed logged in already
    });

  }

}
