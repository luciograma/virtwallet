package com.asj.virtwalletapirest.services;

import com.asj.virtwalletapirest.domain.Cuenta;
import com.asj.virtwalletapirest.domain.Transaccion;

public interface ITransaccionService {

    Transaccion add(Transaccion transaccion);

    Iterable<Transaccion> getAll();

    Iterable<Transaccion> findByCuentaOrigen(Cuenta cuenta);
    Iterable<Transaccion> findByCuentaDestino(Cuenta cuenta);
}
