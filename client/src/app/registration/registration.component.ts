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
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  @Input()
  token: string | null;
  contactForm;
  valid:number;
  firstName = new FormControl('', [
    Validators.required,
    Validators.minLength(3)
  ]);

  lastName = new FormControl('', [
    Validators.required,
    Validators.minLength(3)
  ]);

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
        firstName: this.firstName,
        lastName: this.lastName,
        username: this.username,
        password: this.password
      });
     }

    

  ngOnInit(): void {
  }


  onSubmit() {
    console.log(this.contactForm.value);
    this.http.post('http://localhost:8080/api/auth/register',this.contactForm.value,{observe:"response"}).subscribe((res) =>{
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
    this.token = localStorage.getItem("token");
  }

  validiraj(): void {
  }

}
