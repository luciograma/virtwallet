import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {LoginComponent} from "./components/login/login.component";
import {TransferComponent} from "./components/transfer/transfer.component";
import {RegisterComponent} from "./components/register/register.component";
import {CuentaComponent} from "./components/cuenta/cuenta.component";
import {TransaccionesComponent} from "./components/transacciones/transacciones.component";

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'transfer', component: TransferComponent},
  {path: 'transfer/:dni', component: TransferComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: HomeComponent},
  {path: 'home/:dni', component: HomeComponent},
  {path: 'cuentas', component: CuentaComponent},
  {path: 'transacciones', component: TransaccionesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
