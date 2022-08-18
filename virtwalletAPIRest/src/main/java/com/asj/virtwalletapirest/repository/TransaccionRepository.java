package com.asj.virtwalletapirest.repository;

import com.asj.virtwalletapirest.entities.Cuenta;
import com.asj.virtwalletapirest.entities.Transaccion;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransaccionRepository extends PagingAndSortingRepository<Transaccion, Long> {

     Iterable<Transaccion> findAll();
     Iterable<Transaccion> findAllByCuentaOrigen(Cuenta cuenta);
     Iterable<Transaccion> findAllByCuentaDestino(Cuenta cuenta);

}
