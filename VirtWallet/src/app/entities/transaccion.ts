import {Cuenta} from "./cuenta";

export class Transaccion {

  fecha: string;
  id: number;
  concepto: string;
  importe: number;
  cuentaOrigen: Cuenta;
  cuentaDestino: Cuenta;

}
