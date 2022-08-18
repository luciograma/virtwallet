package com.asj.virtwalletapirest.services;

import com.asj.virtwalletapirest.entities.Transaccion;
import com.asj.virtwalletapirest.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionServiceImp implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepo;

    @Override
    public Transaccion add(Transaccion transaccion) {
        return this.transaccionRepo.save(transaccion);
    }

    @Override
    public Iterable<Transaccion> getAll() {
        return this.transaccionRepo.findAll();
    }


}
