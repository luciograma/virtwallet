import {Transaccion} from "./transaccion";

export class Cuenta {
  id: number;
  nombre: string;
  apellido: string;
  cbu: string;
  alias: string;
  dni: string;
  saldo: number;
  transferenciasRecibidas: Transaccion[];
  transferenciasEnviadas: Transaccion[];
  password: string;

}
