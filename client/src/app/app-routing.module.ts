import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MojiOglasiComponent } from './moji-oglasi/moji-oglasi.component';
import { OmiljeniComponent } from './omiljeni/omiljeni.component';
import { LoginComponent } from './login/login.component';
import { PocetnaStranaComponent } from './pocetna-strana/pocetna-strana.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AdDetailsComponent } from './ad-details/ad-details.component';
import { ZakazivanjeComponent } from './zakazivanje/zakazivanje.component';
const routes: Routes = [
  {path: 'pocetnaStrana', component: PocetnaStranaComponent},
  {path: 'omiljeni', component: OmiljeniComponent},
  {path: 'mojiOglasi', component: MojiOglasiComponent},
  {path: 'login', component: LoginComponent},
  {path: 'ad-details/:id', component:AdDetailsComponent},
  {path:'zakazivanje',component:ZakazivanjeComponent},
  { path: '', redirectTo: 'pocetnaStrana', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
