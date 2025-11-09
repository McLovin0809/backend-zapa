package com.ecomerce.zapa.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
public class AuditoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_auditoriaProducto;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario; // quién realizó el cambio amin

    @Column(nullable = false)
    private Double precioAnterior;

    @Column(nullable = false)
    private Double precioNuevo;

    private Double descuento; 

    @Column(nullable = false)
    private LocalDateTime fechaCambio = LocalDateTime.now();

    @Column(length = 255)
    private String motivo; // opcional, por qué se cambió

}
