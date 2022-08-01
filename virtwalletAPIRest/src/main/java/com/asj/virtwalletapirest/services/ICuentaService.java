package com.asj.virtwalletapirest.services;

import com.asj.virtwalletapirest.domain.Cuenta;

import java.util.Optional;

public interface ICuentaService {

    Iterable<Cuenta> getAll();
    Optional<Cuenta> getCuentaById(Long id);
    Optional<Cuenta> getCuentaByDni(String dni);
    Cuenta add(Cuenta cuenta);

    Optional<Cuenta> getCuentaByAlias(String alias);

    Optional<Cuenta> getCuentaByCbu(String cbu);
}
