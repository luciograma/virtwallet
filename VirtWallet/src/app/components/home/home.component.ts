import {Component, Input, OnInit} from '@angular/core';
import {Cuenta} from "../../entities/cuenta";
import {ActivatedRoute, Router} from "@angular/router";
import {TransaccionService} from "../../services/transaccion.service";
import {Transaccion} from "../../entities/transaccion";
import {DataCuentaService} from "../../services/data-cuenta.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  dni: string;
  cuenta: any;
  transacciones: any;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private cuentaService: DataCuentaService,
              private transaccionService: TransaccionService) {
    route.params.subscribe(
      params => {
        this.dni = params['dni'];
        console.log('DNI de la url: ' + this.dni);
      }
    )
    this.cuentaService.obtenerCuentaPorDni(this.dni).subscribe(dato => {
      this.cuenta = dato.datos;
      console.log(this.cuenta);
    });
    // this.transacciones=this.cuenta.transaccionesEnviadas;
    // this.transacciones=this.cuenta.transaccionesRecibidas;

  }

  ngOnInit(): void {
  }

  transferir() {
    this.router.navigate(["transfer/" + this.dni]);
  }

  cerrarSesion() {
    this.router.navigate(['']);
  }

}
