import {Component, OnInit} from '@angular/core';
import {Cuenta} from "../../entities/cuenta";
import {DataCuentaService} from "../../services/data-cuenta.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cuenta',
  templateUrl: './cuenta.component.html',
  styleUrls: ['./cuenta.component.css']
})
export class CuentaComponent implements OnInit {

  cuentas: any;
  // cuentas: Cuenta[];

  constructor(private cuentaService: DataCuentaService, private router: Router) {
  }

  ngOnInit(): void {
    this.getAllCuentas();
  }

  private getAllCuentas() {
    this.cuentaService.obtenerListaCuentas().subscribe(dato=>{
      this.cuentas=dato.datos;
      console.log(dato.datos)})
  }

  irLogin() {
    this.router.navigate(['']);
  }
}

