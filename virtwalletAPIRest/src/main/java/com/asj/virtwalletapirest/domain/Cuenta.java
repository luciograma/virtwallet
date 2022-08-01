package com.asj.virtwalletapirest.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "cuentas")
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String apellido;

    @Column(unique = true, nullable = false, length = 10)
    private String cbu;

    @Column(unique = true, nullable = false, length = 20)
    private String alias;

    @Column(unique = true, nullable = false, length = 8)
    private String dni;

    @Column(nullable = false)
    private Double saldo;

    @Column(nullable = false, length = 10)
    private String password;

    @OneToMany(mappedBy = "cuentaOrigen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaccion> transferenciasEnviadas;

    @OneToMany(mappedBy = "cuentaDestino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaccion> transferenciasRecibidas;

}
