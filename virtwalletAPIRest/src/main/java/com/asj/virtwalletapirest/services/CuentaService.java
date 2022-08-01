package com.asj.virtwalletapirest.services;

import com.asj.virtwalletapirest.domain.Cuenta;
import com.asj.virtwalletapirest.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService implements ICuentaService {

    @Autowired
    private CuentaRepository repository;

    @Override
    public Iterable<Cuenta> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Cuenta> getCuentaById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Cuenta> getCuentaByDni(String dni) {
        return this.repository.findCuentaByDni(dni);
    }

    @Override
    public Cuenta add(Cuenta cuenta) {
        return this.repository.save(cuenta);
    }

    @Override
    public Optional<Cuenta> getCuentaByAlias(String alias) {
        return this.repository.findCuentaByAlias(alias);
    }

    @Override
    public Optional<Cuenta> getCuentaByCbu(String cbu) {
        return this.repository.findCuentaByCbu(cbu);
    }


}
