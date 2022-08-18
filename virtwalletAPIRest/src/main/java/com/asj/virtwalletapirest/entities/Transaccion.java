package com.asj.virtwalletapirest.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "transaccionesEnviadas")
    private Cuenta cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "transaccionesRecibidas")
    private Cuenta cuentaDestino;

    @Column(length = 100)
    private String concepto;

    @Column(nullable = false)
    private Double importe;

}
