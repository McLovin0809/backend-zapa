package com.ecomerce.zapa.model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auditoria_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_auditoriaVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    private Double totalVenta;
    private Double ganancia;
    private LocalDateTime fechaRegistro = LocalDateTime.now();

}
