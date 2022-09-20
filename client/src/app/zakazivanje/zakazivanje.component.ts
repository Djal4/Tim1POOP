import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-zakazivanje',
  templateUrl: './zakazivanje.component.html',
  styleUrls: ['./zakazivanje.component.css']
})
export class ZakazivanjeComponent implements OnInit {

  constructor(private router: Router) { }
  owner:boolean;
  baseUrl:string;
  requestedSightseeings:any[];
  ownerSightseeings:any[];
  sightseeingCategories:any[];
  ngOnInit(): void {
    this.baseUrl="http://localhost:8080/api/sightseeing/";
    this.owner=true;
    if(localStorage.getItem("token")==null)
      this.router.navigate(["/"]);
    this.sightseeingCategories=[
      {
        id:0,
        name:"All",
        condition:(sightseeing:any)=>true
      },
      {
        id:1,
        name:"Unknown",
        condition:(sightseeing:any)=>sightseeing.accepted===null
      },
      {
        id:2,
        name:"Accepted",
        condition:(sightseeing:any)=>sightseeing.accepted===true
      },
      {
        id:3,
        name:"Rejected",
        condition:(sightseeing:any)=>sightseeing.accepted===false
      },
      {
        id:4,
        name:"Marked and commented",
        query:(sightseeing:any)=>sightseeing.mark>0
      }
    ];
    fetch(this.baseUrl+"owner",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
    .then(response=>{
      this.failedAuth(response);
      return response.json();
    })
    .then(response=>{
      this.ownerSightseeings=response;
    })
    .catch(console.error);

    fetch(this.baseUrl+"user",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
    .then(response=>{
      this.failedAuth(response);
      return response.json();
    })
    .then(response=>{
      this.requestedSightseeings=response;
    })
    .catch(console.error);
  }
  failedAuth(response:any){
    if(response.status===401){
      localStorage.removeItem("token");
      this.router.navigate(["/"]);
    }
  }
  getStatus(sightseeing:any){
    if(sightseeing.accepted===null)
      return "Unknown";
    if(sightseeing.accepted===false)
      return "Rejected";
    if(sightseeing.accepted===true && sightseeing.mark==0)
      return "Accepted";

    return "Marked and commented";
  }
  acceptOrReject(sight:any,b:boolean){
    const url=this.baseUrl+((b)?"accept":"reject")+"/"+sight.id;
    fetch(url,{headers:{"Authorization":"Bearer "+localStorage.getItem("token")},method:"PATCH"})
    .then(response=>{
      this.failedAuth(response);
      return response.json();
    })
    .then(response=>{
      sight.accepted=b;
    })
    .catch(console.error);
  }

  setOwner(event:any){
    document.getElementsByClassName("active-tab")[0].classList.remove("active-tab");
    event.target.classList.add("active-tab");
    this.owner=true;
  }

  setUser(event:any){
    document.getElementsByClassName("active-tab")[0].classList.remove("active-tab");
    event.target.classList.add("active-tab");
    this.owner=false;
  }
}
