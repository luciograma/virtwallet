package com.asj.virtwalletapirest.repository;

import com.asj.virtwalletapirest.entities.Cuenta;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CuentaRepository extends PagingAndSortingRepository<Cuenta, Long> {

    Iterable<Cuenta> findAll();
    Optional<Cuenta> findCuentaByDni(String dni);
    Optional<Cuenta> findCuentaByAlias(String alias);
    Optional<Cuenta> findCuentaByCbu(String cbu);
}
