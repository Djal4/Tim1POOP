import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ad } from '../modeli/ad';
import { OglasiService } from '../services/oglasi.service';
export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];


@Component({
  selector: 'app-moji-oglasi',
  templateUrl: './moji-oglasi.component.html',
  styleUrls: ['./moji-oglasi.component.scss']
})

export class MojiOglasiComponent implements OnInit {

  displayedColumns: string[] = ['Kategorija', 'Grad', 'Drzava', 'Kvadratura', 'Cena', 'Uredi', 'Obrisi'];
  dataSource = ELEMENT_DATA;
  myAds:Ad[];
  constructor(private router:Router,
              private oglasiService:OglasiService) { }

  valid:number = 0;
  ngOnInit(): void {
    
    this.oglasiService.getMyAds().subscribe(response => {
      console.log("Response received");
      this.myAds = response;
      console.log(this.myAds);
      this.valid = 1;
    },
    error =>{
      alert("Morate se ulogovati!");
      this.router.navigate(["/login"]);
    })


  }

}
