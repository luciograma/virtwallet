package com.asj.virtwalletapirest.domain.dto;

import com.asj.virtwalletapirest.domain.Cuenta;
import lombok.*;

import java.util.Date;

@Data //agrega todos los metodos
public class TransaccionDTO {

    private Long id;
    private Date fecha;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private String concepto;
    private Double importe;

}
