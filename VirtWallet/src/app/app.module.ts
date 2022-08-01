import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {TransferComponent} from './components/transfer/transfer.component';
import {HomeComponent} from './components/home/home.component';
import {RouterModule, Routes} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ErrorComponent} from './components/error/error.component';
import {TransaccionService} from "./services/transaccion.service";
import {DataCuentaService} from "./services/data-cuenta.service";
import {HttpClientModule} from "@angular/common/http";
import {CuentaComponent} from "./components/cuenta/cuenta.component";
import { TransaccionesComponent } from './components/transacciones/transacciones.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'transfer', component: TransferComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: HomeComponent},
  //** indica que cualquier cosa distinta a lo anterior irá aquí
  {path: '**', component: ErrorComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    TransferComponent,
    HomeComponent,
    CuentaComponent,
    ErrorComponent,
    TransaccionesComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    TransaccionService, DataCuentaService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
