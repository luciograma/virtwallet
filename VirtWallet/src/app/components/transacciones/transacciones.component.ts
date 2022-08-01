import {Component, OnInit} from '@angular/core';
import {TransaccionService} from "../../services/transaccion.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-transacciones',
  templateUrl: './transacciones.component.html',
  styleUrls: ['./transacciones.component.css']
})
export class TransaccionesComponent implements OnInit {

  transacciones: any;

  constructor(private service: TransaccionService, private router: Router) {
  }

  ngOnInit(): void {
    this.getAllTransacciones();
  }

  private getAllTransacciones() {
    this.service.obtenerListaTransacciones().subscribe(dato => {
      this.transacciones = dato.datos;
      console.log(dato.datos);
    });
  }

  irLogin() {
    this.router.navigate(['']);
  }
}
