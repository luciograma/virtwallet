package com.asj.virtwalletapirest.controller;

import com.asj.virtwalletapirest.entities.Cuenta;
import com.asj.virtwalletapirest.entities.Transaccion;
import com.asj.virtwalletapirest.services.CuentaService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties()
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    Logger logger = LoggerFactory.getLogger(CuentaController.class);

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<Cuenta> cuentas = (List<Cuenta>) this.cuentaService.getAll();
        Map<String, Object> mensajeBody = new HashMap<>();
        if (cuentas.isEmpty()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No se hallaron datos");
            return ResponseEntity
                    .badRequest()
                    .body(mensajeBody);
        }
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", cuentas);
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> getByDni(@PathVariable String dni) {
        Map<String, Object> mensajeBody = new HashMap<>();
        Optional<Cuenta> cuentaOptional = this.cuentaService.getCuentaByDni(dni);
        return getResponseEntity(mensajeBody, cuentaOptional);
    }

    @GetMapping("/alias/{alias}")
    public ResponseEntity<?> getByAlias(@PathVariable String alias) {
        Map<String, Object> mensajeBody = new HashMap<>();
        Optional<Cuenta> cuentaOptional = this.cuentaService.getCuentaByAlias(alias);
        return getResponseEntity(mensajeBody, cuentaOptional);
    }

    @GetMapping("/cbu/{cbu}")
    public ResponseEntity<?> getByCbu(@PathVariable String cbu) {
        Map<String, Object> mensajeBody = new HashMap<>();
        Optional<Cuenta> cuentaOptional = this.cuentaService.getCuentaByCbu(cbu);
        return getResponseEntity(mensajeBody, cuentaOptional);
    }

    @GetMapping("/movimientos/{dni}")
    public ResponseEntity<?> getTransacciones(@PathVariable String dni) {
        Optional<Cuenta> cuenta = this.cuentaService.getCuentaByDni(dni);
        List<Transaccion> transacciones = new ArrayList<>();
        if (cuenta.isPresent()) {
            List<Transaccion> transferenciasRecibidas = cuenta.get().getTransferenciasRecibidas();
            List<Transaccion> transferenciasEnviadas = cuenta.get().getTransferenciasEnviadas();
            transacciones.addAll(transferenciasEnviadas);
            transacciones.addAll(transferenciasRecibidas);
        }
        //TODO ordenar lista por fecha
        //        transacciones.sort();
        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", transacciones);
        return ResponseEntity.ok(mensajeBody);
    }

    @PostMapping("")
    public ResponseEntity<?> altaCuenta(@RequestBody Cuenta cuenta) {
        Map<String, Object> mensajeBody = new HashMap<>();
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", this.cuentaService.add(cuenta));
        return new ResponseEntity<>(mensajeBody, HttpStatus.CREATED);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<?> update(@PathVariable String dni, @RequestBody Cuenta cuentaModificada) {
        Map<String, Object> mensajeBody = new HashMap<>();
        if (!this.cuentaService.getCuentaByDni(dni).isPresent()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "La cuenta no existe");
            return ResponseEntity.badRequest().body(mensajeBody);
        } else {
            mensajeBody.put("estado", Boolean.TRUE);
            Cuenta cuentaModificar = cuentaModificada;
            mensajeBody.put("datos", this.cuentaService.update(cuentaModificar));
        }
        return ResponseEntity.ok(mensajeBody);
    }

    private ResponseEntity<?> getResponseEntity(Map<String, Object> mensajeBody, Optional<Cuenta> cuentaOptional) {
        if (!cuentaOptional.isPresent()) {
            mensajeBody.put("estado", Boolean.FALSE);
            mensajeBody.put("mensaje", "No se hallaron datos");
            return ResponseEntity
                    .badRequest()
                    .body(mensajeBody);
        }
        mensajeBody.put("estado", Boolean.TRUE);
        mensajeBody.put("datos", cuentaOptional.get());
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping("/log")
    public void usoDeLog() {
        logger.info("Log TRACE");
        logger.info("Log DEBUG");
        logger.info("Log INFO");
        logger.info("Log WARNING");
        logger.info("Log ERROR");

    }
}
