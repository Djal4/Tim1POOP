import { Component, Input, OnInit } from '@angular/core';
import { ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { groupBy } from 'rxjs/internal/operators/groupBy';
import { LoginService } from '../services/login.service';
import { HttpClient } from '@angular/common/http';
import { isEmpty } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  @Input()
  token: string | null;
  contactForm;
  username = new FormControl('', [
    Validators.required,
    Validators.minLength(4)
  ]);

  password = new FormControl('', [Validators.required]);

  constructor(private formBuilder: FormBuilder,
    private loginService: LoginService,
    private http: HttpClient,
    private router: Router) {



    this.contactForm = this.formBuilder.group({
      username: this.username,
      password: this.password
    });
  }

  ngOnInit(): void {
  }

  get firstname() {
    return this.contactForm.get('firstname');
  }

  get lastname() {
    return this.contactForm.get('lastname');
  }

  onSubmit() {
    this.loginService.GetLogin(this.contactForm.value);
    this.token = localStorage.getItem("token");
    this.router.navigate(["/"]);
  }

  validiraj(): void {
  }

}




