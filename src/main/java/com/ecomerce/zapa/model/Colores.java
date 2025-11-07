package com.ecomerce.zapa.model;

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
@Table(name = "colores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Colores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_colores;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

}
