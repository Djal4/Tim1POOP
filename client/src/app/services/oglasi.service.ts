import { Injectable } from '@angular/core';
import { Ad } from '../modeli/ad';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class OglasiService {



  constructor(private http: HttpClient) { }

  public getAllAds(): Observable<Ad[]> {
    return this.http.get<Ad[]>("http://localhost:8080/api/ads");
  }

  public getMyAds(): Observable<Ad[]> {
    return this.http.get<Ad[]>("http://localhost:8080/api/ads/my", { headers: { "Authorization": "Bearer " + localStorage.getItem("token") } });
  }

  public getAdById(id:number):Observable<Ad>{
    return this.http.get<Ad>('http://localhost:8080/api/ads/'+id);
  }

}
