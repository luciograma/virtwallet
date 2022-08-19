package com.asj.virtwalletapirest.services;

import com.asj.virtwalletapirest.entities.Cuenta;

import java.util.Optional;

public interface CuentaService {

    Iterable<Cuenta> getAll();
    Optional<Cuenta> getCuentaByDni(String dni);
    Cuenta add(Cuenta cuenta);

    Optional<Cuenta> getCuentaByAlias(String alias);

    Optional<Cuenta> getCuentaByCbu(String cbu);

    Cuenta update(String dni, Cuenta cuentaModificar);
}
