import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataCuentaService {

  private baseURL = "http://localhost:8080/api/v1/virtwallet/cuentas";

  constructor(private http: HttpClient) {
  }

  obtenerListaCuentas():Observable<any>{
    return this.http.get<any>(`${this.baseURL}`);
  }

  registrarCuenta(cuenta):Observable<Object>{
    return this.http.post(`${this.baseURL}`,cuenta);
  }

  actualizarCuenta(dni:string, cuenta): Observable<Object>{
    return this.http.put(`${this.baseURL}/${dni}`, dni, cuenta);
  }

  obtenerCuentaPorDni(dni:string):Observable<any>{
    return this.http.get<any>(`${this.baseURL}/${dni}`);
  }

  obtenerCuentaPorAlias(alias: string):Observable<any> {
    return this.http.get<any>(`${this.baseURL}/alias/${alias}`);
  }

  obtenerCuentaPorCbu(cbu: string): Observable<any> {
    return this.http.get<any>(`${this.baseURL}/cbu/${cbu}`);
  }

  obtenerMovimientos(dni: string): Observable<any>{
    return this.http.get<any>(`${this.baseURL}/movimientos/${dni}`);
  }
}
