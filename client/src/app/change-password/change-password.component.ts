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
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  @Input()
  token: string | null;
  contactForm;
  valid:number;
  
  oldPassword = new FormControl('', [Validators.required]);
  newPassword = new FormControl('', [Validators.required]);
  newPasswordConfirmed = new FormControl('', [Validators.required]);

  constructor(private formBuilder: FormBuilder,
    private loginService: LoginService,
    private http: HttpClient,
    private router: Router) { 
      this.contactForm = this.formBuilder.group({
        oldPassword: this.oldPassword,
        newPassword: this.newPassword,
        newPasswordConfirmed: this.newPasswordConfirmed
      });
    }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.contactForm.value);
    this.http.put('http://localhost:8080/api/auth/change/password',this.contactForm.value,{ headers: { "Authorization": "Bearer " + localStorage.getItem("token") },observe:"response"}).subscribe((res) =>{
      this.valid = 1;
      localStorage.setItem("token",JSON.parse(JSON.stringify(res.body)).token);
      setTimeout(()=>{
        this.router.navigate(["/login"]);
      },1000);
  },
  (error)=>{
    this.valid = 0;
    console.log("error caught in component");
    console.error(error);
    console.log(this.valid);
    
  })
  }

  validiraj(): void {
  }

}
