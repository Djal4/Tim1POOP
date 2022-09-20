import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { map } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  t = localStorage.getItem("accessToken");


  headers = new HttpHeaders({
    "Authorization":"Bearer-token"
  })
  constructor(private http:HttpClient) { }


  GetLogin(object:Object) {
    this.http.post('http://localhost:8080/api/auth/login',JSON.stringify(object),{headers:{"Content-Type":"application/json"},observe:"response"})
      .subscribe((res) =>{
          localStorage.setItem("token",JSON.parse(JSON.stringify(res.body)).token);
      },
      (error)=>{
        console.log("error caught in component");
        console.error(error);
      })
    }
    

  }