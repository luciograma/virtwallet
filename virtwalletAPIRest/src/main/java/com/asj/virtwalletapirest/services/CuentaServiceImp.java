package com.asj.virtwalletapirest.services;

import com.asj.virtwalletapirest.entities.Cuenta;
import com.asj.virtwalletapirest.entities.Transaccion;
import com.asj.virtwalletapirest.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImp implements CuentaService {

    @Autowired
    private CuentaRepository repository;

    @Override
    public Iterable<Cuenta> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Cuenta> getCuentaByDni(String dni) {
        return this.repository.findCuentaByDni(dni);
    }

    @Override
    public Cuenta add(Cuenta cuenta) {
        if (getCuentaByDni(cuenta.getDni()).isPresent()) {
            //TODO corregir tipo de exception
            throw new RuntimeException();
        }
        cuenta.setCbu("00" + cuenta.getDni());
        cuenta.setAlias(cuenta.getNombre().toUpperCase() + "." + cuenta.getApellido().toUpperCase());
        cuenta.setSaldo(0.0);
        List<Transaccion> enviadas = new ArrayList();
        List<Transaccion> recibidas = new ArrayList();
        cuenta.setTransferenciasEnviadas(enviadas);
        cuenta.setTransferenciasRecibidas(recibidas);
        return this.repository.save(cuenta);
    }

    @Override
    public Cuenta update(Cuenta cuentaModificar) {
        Optional<Cuenta> co =getCuentaByDni(cuentaModificar.getDni());
        if (!co.isPresent()) {
            //TODO corregir tipo de exception
            throw new RuntimeException();
        }
        Cuenta cuenta = co.get();
        cuenta.setSaldo(cuentaModificar.getSaldo());
        cuenta.setTransferenciasEnviadas(cuentaModificar.getTransferenciasEnviadas());
        cuenta.setTransferenciasRecibidas(cuentaModificar.getTransferenciasRecibidas());
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
