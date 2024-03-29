import { Component, OnInit } from '@angular/core';
import { User } from '../modeli/user';
import { Ad } from '../modeli/ad';
import { OglasiService } from '../services/oglasi.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-pocetna-strana',
  templateUrl: './pocetna-strana.component.html',
  styleUrls: ['./pocetna-strana.component.scss']
})
export class PocetnaStranaComponent implements OnInit {

  korisnici:User[] = [];
  ads: Ad[];
  allAds: any;
  constructor(private oglasiService:OglasiService,
    private router:Router) {
    
   }

   showDetails(id:number){
      this.router.navigate(["/ad-details",id]);
   }

  ngOnInit(): void {
    this.oglasiService.getAllAds().subscribe(response => {
      console.log("Response received");
      this.ads = response;
      this.allAds = JSON.parse(JSON.stringify(this.ads));
      console.log(this.ads);
      console.log(this.allAds);
    },
    error =>{
      console.error(error);
    })
  }



}
