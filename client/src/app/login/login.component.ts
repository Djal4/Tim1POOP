import { Component, OnInit } from '@angular/core';
import {ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { FormBuilder } from '@angular/forms'
import { groupBy } from 'rxjs/internal/operators/groupBy';
import { LoginService } from '../services/login.service';
import { HttpClient } from '@angular/common/http';
import { isEmpty } from 'rxjs';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  contactForm;
  token:any;
  constructor(private formBuilder: FormBuilder,
              private loginService: LoginService,
              private http:HttpClient) {
 
    this.contactForm = this.formBuilder.group({
      username: ['',[Validators.required, Validators.minLength(10)]],
      password: ['',[Validators.required, Validators.maxLength(15), Validators.pattern("^[a-zA-Z]+$")]]
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
  }

  validiraj():void{
  }
 
}
 



