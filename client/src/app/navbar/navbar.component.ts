import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  constructor(private router:Router) { }

  token = localStorage.getItem("token");

  ngOnInit(): void {
  }

  logOut():void{
    localStorage.removeItem("token");
    this.router.navigate(["/pocetnaStrana"]);
  }

  

}
