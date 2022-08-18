import {Injectable} from '@angular/core';
import {Transaccion} from "../entities/transaccion";
import {DataCuentaService} from "./data-cuenta.service";
import {HttpClient} from "@angular/common/http";
import {Cuenta} from "../entities/cuenta";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TransaccionService {

  private baseURL = "http://localhost:8080/cuentas/transacciones";

  constructor(private http: HttpClient) {
  }

  obtenerListaTransacciones():Observable<any>{
    return this.http.get<any>(`${this.baseURL}`);
  }

  agregarTransaccion(transaccion):Observable<Object>{
    return this.http.post(`${this.baseURL}`,transaccion);
  }

}
