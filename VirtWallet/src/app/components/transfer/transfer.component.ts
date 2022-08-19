import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Transaccion} from "../../entities/transaccion";
import {TransaccionService} from "../../services/transaccion.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Cuenta} from "../../entities/cuenta";
import {DataCuentaService} from "../../services/data-cuenta.service";

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  dni: string;
  transferForm: FormGroup;
  cbu: string;
  alias: string;
  importe: number;
  concepto: string;

  cuentaOrigen: Cuenta;
  cuentaDestino: Cuenta;
  transaccion: Transaccion = new Transaccion();

  constructor(private router: Router, private route: ActivatedRoute, private formBuilder: FormBuilder,
              private cuentaService: DataCuentaService, private transaccionService: TransaccionService) {
  }

  ngOnInit(): void {
    this.transferForm = this.formBuilder.group({
      cbu: ['', Validators.minLength(10)],
      alias: ['', Validators.minLength(11)],
      importe: ['', Validators.compose([Validators.required, Validators.min(1)])],
      concepto: ['', Validators.maxLength(50)]
    });
    this.route.params.subscribe(
      params => {
        this.dni = params['dni'];
        console.log('DNI de la url: ' + this.dni);
      });

    //Obtengo cuenta origen
    this.cuentaService.obtenerCuentaPorDni(this.dni).subscribe(dato => {
      this.cuentaOrigen = dato.datos;
      console.log("Cuenta origen: ");
      console.log(this.cuentaOrigen);
    });
  }

  transferir() {
    if (this.transferForm.valid) {
      this.realizarTransaccion();
    }
  }

  private realizarTransaccion() {
    // TODO activar cuando funcione con cbu
    //  if (this.transferForm.value.alias !== null) {
    //   this.cuentaService.obtenerCuentaPorAlias(this.transferForm.value.alias).subscribe(dato => {
    //     this.cuentaDestino = dato.datos;
    //     this.procesar();
    //     console.log("Cuenta destino: "+this.cuentaDestino);
    //   });
    //   }
    if (this.transferForm.value.cbu !== null) {
      this.cuentaService.obtenerCuentaPorCbu(this.transferForm.value.cbu).subscribe(dato => {
        this.cuentaDestino = dato.datos;
        this.procesar();
        console.log("Cuenta destino: "+this.cuentaDestino);
      });
    }
  }

  private procesar(){
    if (this.cuentaDestino !== undefined) {
      this.acreditar(this.transferForm.value.importe);
      this.debitar(this.transferForm.value.importe);
      //Registro transaccion
      this.registrarTransaccion();
      //TODO llevar al back a un solo servicio
      this.cuentaService.actualizarCuenta(this.cuentaOrigen.dni, this.cuentaOrigen).subscribe(resp=>{
        console.log("Cuenta origen actualizada");
      });
      this.cuentaService.actualizarCuenta(this.cuentaDestino.dni, this.cuentaDestino).subscribe(resp=>{
        console.log("Cuenta origen actualizada");
      });
      console.log("Transaccion realizada con exito!")
    } else {
      console.log("La transaccion no ha sido realizada")
    }
    this.volverHome();
  }

  private acreditar(importe: number) {
    console.log("Saldo cuentaDestino: "+this.cuentaDestino.saldo);
    this.cuentaDestino.saldo = this.cuentaDestino.saldo + importe;
    console.log("Saldo destino actualizado: "+this.cuentaDestino.saldo);
  }

  private debitar(importe: number) {
    if (this.cuentaOrigen.saldo >= importe) {
      console.log("Saldo cuentaOrigen: "+this.cuentaOrigen.saldo);
      this.cuentaOrigen.saldo = this.cuentaOrigen.saldo - importe;
      console.log("Saldo origen actualizado: "+this.cuentaOrigen.saldo);
    }
  }

  private registrarTransaccion() {
    // this.crearTransaccion();
    this.transaccion.fecha = new Date();
    this.transaccion.concepto = this.transferForm.value.concepto;
    this.transaccion.importe = this.transferForm.value.importe;
    // this.transaccion.cuentaOrigen = this.cuentaOrigen;
    // this.transaccion.cuentaDestino = this.cuentaDestino;

    //Creo un array nuevo de transacciones = a las transacciones de cuenta origen y agrego la nueva
    if (this.cuentaDestino.transferenciasRecibidas == undefined) {
      this.cuentaDestino.transferenciasRecibidas = [];
    }
    if (this.cuentaOrigen.transferenciasEnviadas == undefined) {
      this.cuentaOrigen.transferenciasEnviadas = [];
    }

    this.cuentaOrigen.transferenciasEnviadas.push(this.transaccion);
    this.cuentaDestino.transferenciasRecibidas.push(this.transaccion);

    //Persisto los cambios
    console.log(this.cuentaOrigen.transferenciasEnviadas);
    console.log(this.cuentaDestino.transferenciasRecibidas);
    this.transaccionService.agregarTransaccion(this.transaccion).subscribe(resp=>{
      console.log("Transaccion agregada")
    });
  }

  volverHome() {
    this.router.navigate(['home/' + this.dni]);
  }

}
