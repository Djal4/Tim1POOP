import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { MojiOglasiComponent } from './moji-oglasi/moji-oglasi.component';
import { OmiljeniComponent } from './omiljeni/omiljeni.component';
import { PocetnaStranaComponent } from './pocetna-strana/pocetna-strana.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular-material.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { ZakazivanjeComponent } from './zakazivanje/zakazivanje.component';
import { FormsModule } from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { AdDetailsComponent } from './ad-details/ad-details.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    MojiOglasiComponent,
    OmiljeniComponent,
    PocetnaStranaComponent,
    ZakazivanjeComponent,
    RegistrationComponent,
    ChangePasswordComponent,
    AdDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
