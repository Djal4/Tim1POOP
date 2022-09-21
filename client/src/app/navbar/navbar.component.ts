import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  constructor(private router: Router) { }
  token: string | null;
  ngOnInit(): void {
    this.token = localStorage.getItem("token");
  }

  logOut(): void {
    localStorage.removeItem("token");
    this.router.navigate(["/pocetnaStrana"]);
  }



}
