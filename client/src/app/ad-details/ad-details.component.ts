import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ad } from '../modeli/ad';
import { ActivatedRoute } from '@angular/router';
import { OglasiService } from '../services/oglasi.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { Router } from '@angular/router';
@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrls: ['./ad-details.component.scss']
})
export class AdDetailsComponent implements OnInit {

  selectedAd:Ad;
  selectedAdDetail:any;
  komentari:any;
  date:any;
  time:any;
  objekatZakazivanje:any = {};
  constructor(private http:HttpClient,
             private route: ActivatedRoute,
             private oglasiService:OglasiService,
             private router:Router) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.oglasiService.getAdById(id).subscribe(response => {
      console.log("Response received");
      this.selectedAd = response;
      this.selectedAdDetail = JSON.parse(JSON.stringify(this.selectedAd));
      this.komentari = JSON.parse(JSON.stringify(this.selectedAdDetail.comments));
      console.log(this.komentari);
      console.log(this.selectedAd);
      console.log(this.selectedAdDetail);
    },
    error =>{
      console.error(error);
    })
  }

  preventDefault(event: any) {
    event.preventDefault();
  }

  zatraziZakazivanje(event:any):void{
    this.preventDefault(event);
    let formData = new FormData(document.getElementById("zakazi_razgledanje")as HTMLFormElement);
    let time = formData.get("time")as any;
    let date = formData.get("date")as any;
    console.log(time);
    console.log(date);
    let obj = new Object;
    this.objekatZakazivanje.adId = Number(this.route.snapshot.paramMap.get('id'));
    this.objekatZakazivanje.time=date+" "+time+":00";
    console.log(this.objekatZakazivanje);
    this.http.post('http://localhost:8080/api/sightseeing/request',this.objekatZakazivanje,{ headers: { "Authorization": "Bearer " + localStorage.getItem("token") } }).subscribe((res) =>{
      console.log(res);
  },
  (error)=>{
    alert("Greska u odabiru datuma ili vremena!");
    console.log("error caught in component");
    console.error(error);
    
  })
  }

}
