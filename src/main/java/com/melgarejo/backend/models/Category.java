package com.melgarejo.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name="category")
public class Category implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="El nombre del producto no puede ir vacio")
    @Size(min = 4, max = 12 , message = "El tamaño del nombre del producto debe estar entre 4 y 12 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message="El nombre del producto no puede ir vacio")
    @Size(min = 4, max = 20 , message = "El tamaño del nombre del producto debe estar entre 4 y 12 caracteres")
    @Column(nullable = false)
    private String descripcion;

    private static final long serialVersionUID= 1L;
}
