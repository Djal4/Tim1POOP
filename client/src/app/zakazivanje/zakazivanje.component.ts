import { Component, OnInit } from '@angular/core';
import { throwMatDuplicatedDrawerError } from '@angular/material/sidenav';
import { Router } from '@angular/router';

@Component({
  selector: 'app-zakazivanje',
  templateUrl: './zakazivanje.component.html',
  styleUrls: ['./zakazivanje.component.css']
})
export class ZakazivanjeComponent implements OnInit {

  constructor(private router: Router) { }
  owner: boolean;
  baseUrl: string;
  requestedSightseeings: any[];
  ownerSightseeings: any[];
  sightseeingCategories: any[];
  sightseeingsToShow: any[];
  filteredSightSeeings:any[];
  ngOnInit(): void {
    this.baseUrl = "http://localhost:8080/api/sightseeing/";
    this.owner = true;
    if (localStorage.getItem("token") == null)
      this.router.navigate(["/"]);
    this.sightseeingCategories = [
      {
        id: 0,
        name: "All",
        condition: (sightseeing: any) => true
      },
      {
        id: 1,
        name: "Pending",
        condition: (sightseeing: any) => sightseeing.accepted === null
      },
      {
        id: 2,
        name: "Accepted",
        condition: (sightseeing: any) => sightseeing.accepted === true && Number(sightseeing.mark)==0
      },
      {
        id: 3,
        name: "Rejected",
        condition: (sightseeing: any) => sightseeing.accepted === false
      },
      {
        id: 4,
        name: "Marked",
        condition: (sightseeing: any) => Number(sightseeing.mark) > 0
      }
    ];
    fetch(this.baseUrl + "owner", { headers: { "Authorization": "Bearer " + localStorage.getItem("token") } })
      .then(response => {
        this.failedAuth(response);
        return response.json();
      })
      .then(response => {
        this.ownerSightseeings = response;
        this.filteredSightSeeings=response;
        this.sightseeingsToShow=response;
      })
      .catch(console.error);

    fetch(this.baseUrl + "user", { headers: { "Authorization": "Bearer " + localStorage.getItem("token") } })
      .then(response => {
        this.failedAuth(response);
        return response.json();
      })
      .then(response => {
        this.requestedSightseeings = response;
      })
      .catch(console.error);
  }
  failedAuth(response: any) {
    if (response.status === 401) {
      localStorage.removeItem("token");
      this.router.navigate(["/"]);
    }
  }
  getStatus(sightseeing: any) {
    if (sightseeing.accepted === null)
      return "Pending";
    if (sightseeing.accepted === false)
      return "Rejected";
    if (sightseeing.accepted === true && sightseeing.mark == 0)
      return "Accepted";

    return "Marked";
  }
  acceptOrReject(sight: any, b: boolean) {
    const url = this.baseUrl + ((b) ? "accept" : "reject") + "/" + sight.id;
    fetch(url, { headers: { "Authorization": "Bearer " + localStorage.getItem("token") }, method: "PATCH" })
      .then(response => {
        this.failedAuth(response);
        return response.json();
      })
      .then(response => {
        sight.accepted = b;
      })
      .catch(console.error);
  }
  preventDefault(event: any) {
    event.preventDefault();
  }
  markAndComment(event: any, sight: any) {
    this.preventDefault(event);
    let formData = new FormData(document.getElementById("mark-form") as HTMLFormElement);
    let mark: number = Number(formData.get("mark"));
    let comment: string | undefined = formData.get("comment")?.toString();
    if (comment == '') {
      alert("Comment mustn't be empty!");
      return;
    }
    fetch(this.baseUrl + "mark_and_comment/" + sight.id, { method: "PATCH", body: JSON.stringify({ mark, comment }), headers: { "Content-Type": "application/json", "Authorization": "Bearer " + localStorage.getItem("token") } })
      .then(response => {
        this.failedAuth(response);
        return response.json();
      })
      .then(response => {
        sight.mark = mark;
        sight.comment = comment;
        this.owner = false;
      })
      .catch(console.error);
  }
  setOwner(event: any) {
    document.getElementsByClassName("active-tab")[0].classList.remove("active-tab");
    event.target.classList.add("active-tab");
    this.sightseeingsToShow = this.ownerSightseeings;
    this.filteredSightSeeings=this.sightseeingsToShow;
    this.sightseeingCategories=this.sightseeingCategories;
    this.owner = true;
  }

  setUser(event: any) {
    document.getElementsByClassName("active-tab")[0].classList.remove("active-tab");
    event.target.classList.add("active-tab");
    this.sightseeingsToShow = this.requestedSightseeings;
    this.filteredSightSeeings=this.sightseeingsToShow;
    this.sightseeingCategories=this.sightseeingCategories;
    this.owner = false;
  }
  filterByCategory(event:any){
    let value=Number(event.target.value);
    this.filteredSightSeeings=this.sightseeingsToShow.filter(this.sightseeingCategories[value].condition);
  }
}
