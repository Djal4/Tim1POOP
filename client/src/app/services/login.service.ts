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

  token = localStorage.getItem("token");


  headers = new HttpHeaders({
    "Authorization":"Bearer-token"
  })
  constructor(private http:HttpClient) { }
    

  }