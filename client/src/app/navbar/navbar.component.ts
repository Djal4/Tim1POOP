import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  constructor(private router: Router,public loginService:LoginService) { }
  ngOnInit(): void {
    this.loginService.token=localStorage.getItem("token");
  }

  logOut(): void {
    localStorage.removeItem("token");
    this.loginService.token=null;
    this.router.navigate(["/pocetnaStrana"]);
  }



}
