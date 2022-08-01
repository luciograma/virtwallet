package com.asj.virtwalletapirest.domain.dto;

import com.asj.virtwalletapirest.domain.Transaccion;
import lombok.Data;

import java.util.List;

@Data
public class CuentaDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String cbu;
    private String alias;
    private String dni;
    private Double saldo;
    private String password;
    private List<Transaccion> transferenciasEnviadas;
    private List<Transaccion> transferenciasRecibidas;
}
