package com.asj.virtwalletapirest.services;

import com.asj.virtwalletapirest.domain.Cuenta;
import com.asj.virtwalletapirest.domain.Transaccion;
import com.asj.virtwalletapirest.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransaccionService implements ITransaccionService{

    @Autowired
    private TransaccionRepository repository;

    @Override
    public Transaccion add(Transaccion transaccion) {
        return this.repository.save(transaccion);
    }

    @Override
    public Iterable<Transaccion> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Iterable<Transaccion> findByCuentaOrigen(Cuenta cuenta) {
        return this.repository.findAllByCuentaOrigen(cuenta);
    }

    @Override
    public Iterable<Transaccion> findByCuentaDestino(Cuenta cuenta) {
        return this.repository.findAllByCuentaDestino(cuenta);
    }

}
