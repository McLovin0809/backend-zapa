package com.ecomerce.zapa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer idUsuario;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "elnombre es obligatorio")
    private String nombre;

    @Column(nullable = false, length = 100, unique = true)
    @NotBlank(message = "el email no puede qdar vacio")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    @NotBlank(message = "la clave es obligatoria")
    private String clave;

    @Column(nullable = true, length = 9)
    @Positive(message = "no puede ser negativo")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    @JsonIgnore  // Ignora temporalmente esta relación
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_direccion")
    @JsonIgnore  // Ignora temporalmente esta relación
    private Direccion direccion;

}
