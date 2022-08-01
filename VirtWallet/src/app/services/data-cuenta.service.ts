import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Cuenta} from "../entities/cuenta";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataCuentaService {

  private baseURL = "http://localhost:8080/cuentas";

  constructor(private http: HttpClient) {
  }

  obtenerListaCuentas():Observable<any>{
    return this.http.get<any>(`${this.baseURL}`);
  }

  registrarCuenta(cuenta:Cuenta):Observable<Object>{
    return this.http.post(`${this.baseURL}`,cuenta);
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

  actualizarCuenta(dni:string, cuenta:Cuenta): Observable<any>{
    return this.http.put<any>(`${this.baseURL}/${dni}`, cuenta);
  }
}
