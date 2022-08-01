package com.asj.virtwalletapirest.controller;

import com.asj.virtwalletapirest.domain.Cuenta;
import com.asj.virtwalletapirest.domain.Transaccion;
import com.asj.virtwalletapirest.services.ITransaccionService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transacciones")
@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties(ignoreUnknown = false)
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Transaccion> transacciones = (List<Transaccion>) this.transaccionService.getAll();
        Map<String, Object> mensajeBody = new HashMap<>();
        if (transacciones.isEmpty()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de transacciones");
            return ResponseEntity
                    .badRequest()
                    .body(mensajeBody);
        }
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", transacciones);
        return ResponseEntity.ok(mensajeBody);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Transaccion transaccion) {
        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", this.transaccionService.add(transaccion));
        return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);
    }

    @GetMapping("/{cuenta}")
    public ResponseEntity<?> getAllByCuenta(@PathVariable Cuenta cuenta) {
        List<Transaccion> transacciones = new ArrayList<>();
        List<Transaccion> transaccionesOrigen = (List<Transaccion>) this.transaccionService.findByCuentaOrigen(cuenta);
        for (Transaccion t:transaccionesOrigen) {
            transacciones.add(t);
        }
        List<Transaccion> transaccionesDestino = (List<Transaccion>) this.transaccionService.findByCuentaDestino(cuenta);
        for (Transaccion t:transaccionesDestino) {
            transacciones.add(t);
        }
        Map<String, Object> mensajeBody = new HashMap<>();
        if (transacciones.isEmpty()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No hay datos de transacciones");
            return ResponseEntity
                    .badRequest()
                    .body(mensajeBody);
        }
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", transacciones);
        return ResponseEntity.ok(mensajeBody);
    }

}
