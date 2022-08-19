import {Cuenta} from "./cuenta";

export class Transaccion {

  fecha: Date;
  id: number;
  concepto: string;
  importe: number;
  cuentaOrigen: Cuenta;
  cuentaDestino: Cuenta;

}
