package com.asj.virtwalletapirest.services;

import com.asj.virtwalletapirest.entities.Cuenta;
import com.asj.virtwalletapirest.entities.Transaccion;

public interface TransaccionService {

    Transaccion add(Transaccion transaccion);

    Iterable<Transaccion> getAll();

}
