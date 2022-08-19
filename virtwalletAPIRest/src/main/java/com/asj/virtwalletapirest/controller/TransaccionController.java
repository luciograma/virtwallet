package com.asj.virtwalletapirest.controller;

import com.asj.virtwalletapirest.entities.Transaccion;
import com.asj.virtwalletapirest.services.TransaccionService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties()
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping()
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

    @PostMapping("")
    public ResponseEntity<?> altaTransaccion(@RequestBody Transaccion transaccion) {
        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", this.transaccionService.add(transaccion));
        return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);
    }

}
