import { Input, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MojiOglasiComponent } from './moji-oglasi/moji-oglasi.component';
import { OmiljeniComponent } from './omiljeni/omiljeni.component';
import { LoginComponent } from './login/login.component';
import { PocetnaStranaComponent } from './pocetna-strana/pocetna-strana.component';
import { AdDetailsComponent } from './ad-details/ad-details.component';
import { ZakazivanjeComponent } from './zakazivanje/zakazivanje.component';
import { RegistrationComponent } from './registration/registration.component';
import { UserDetailComponent } from './user-detail/user-detail.component';
const routes: Routes = [
  { path: 'pocetnaStrana', component: PocetnaStranaComponent },
  { path: 'omiljeni', component: OmiljeniComponent },
  { path: 'mojiOglasi', component: MojiOglasiComponent },
  { path: 'login', component: LoginComponent },
  { path: 'ad-details/:id', component: AdDetailsComponent },
  { path: 'zakazivanje', component: ZakazivanjeComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'userDetails/:id', component: UserDetailComponent },
  { path: '', redirectTo: 'pocetnaStrana', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  @Input()
  token: string | null;
}
