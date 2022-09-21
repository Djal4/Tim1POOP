import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ad } from '../modeli/ad';
import { OglasiService } from '../services/oglasi.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { AdCategory } from '../modeli/adCategory';
import { HttpClient } from '@angular/common/http';
import { Country } from '../modeli/country';
import { City } from '../modeli/city';
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

  displayedColumns: string[] = ['Kategorija', 'Grad', 'Drzava', 'Kvadratura', 'Cena', 'Uredi'];
  dataSource = ELEMENT_DATA;
  myAds:Ad[];
  noviOglas:Ad;
  selectedOglas:Ad;
  kategorije:AdCategory[] | null | undefined;
  drzave:Country[] | null;
  gradovi:City[] | undefined;
  closeResult = '';
  constructor(private router:Router,
              private oglasiService:OglasiService,
              private modalService: NgbModal,
              private http:HttpClient) { }
  valid:number;
  ngOnInit(): void {
      this.noviOglas = new Ad;
      this.selectedOglas;
      console.log("novi oglas kreiran");
      console.log(this.noviOglas);
      this.oglasiService.getMyAds().subscribe(response => {
      console.log("Response received");
      console.log(this.kategorije);
      this.myAds = response;
      console.log("MOJI OGLASI");
      console.log(this.myAds);
      this.valid = 1;
    },
    error =>{
      this.valid = 0;
    })
    this.http.get<AdCategory[]>('http://localhost:8080/api/ad_category',{observe:"response"}).subscribe((res) =>{
        this.kategorije = res.body;
        console.log("KATEGORIJE");
        console.log(this.kategorije);
    },
    (error)=>{
      console.log("error caught in component");
      console.error(error);
      
    })

    this.http.get<Country[]>('http://localhost:8080/api/country',{observe:"response"}).subscribe((res) =>{
      this.drzave = res.body;
      console.log("DRZAVE");
      console.log(this.drzave);
      this.drzave?.forEach(element => {
          this.gradovi = element.cities;
      });
    },
    (error)=>{
    console.log("error caught in component");
    console.error(error);
    })

      
}

  goToLogIn():void{
      this.router.navigate(["/login"]);
  }

  open(content:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  open2(content2:any) {
    this.modalService.open(content2, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  onChange(event:any){
    console.log(event.value);
    this.selectedOglas.adCategoryId = event.value;
  }

  otvoriEdit(id:number,content2:any){
    
    this.oglasiService.getAdById(id).subscribe((response:any) => {
      console.log("Response received");
      this.selectedOglas = response;
      // let index =this.kategorije?.findIndex(value =>
      //   value.title == response.adCategory);
      // this.selectedOglas.adCategoryId = this.kategorije?[index].id; 
      console.log("SELECTED OGLAS");
      console.log(this.selectedOglas);
      this.open2(content2);

    },
    error =>{
      console.log("error caught in component");
      console.error(error);
    })
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  kreirajNoviOglas():void{
    console.log(this.noviOglas);
    this.http.post<Ad>('http://localhost:8080/api/ads',this.noviOglas,{ headers: { "Authorization": "Bearer " + localStorage.getItem("token") } }).subscribe((res) =>{
        console.log(res);
    },
    (error)=>{
      console.log("error caught in component");
      console.error(error);
      
    })
  }

  updateOglas():void{
    console.log("NOVI OGLAS");
    console.log(this.selectedOglas);
    this.http.patch<Ad>('http://localhost:8080/api/ads/'+this.selectedOglas.id,this.selectedOglas,{ headers: { "Authorization": "Bearer " + localStorage.getItem("token") } }).subscribe((res) =>{
        console.log(res);
    },
    (error)=>{
      console.log("error caught in component");
      console.error(error);
      
    })
  }


  editujOglas():void{
    console.log(this.selectedOglas);
  }

}
