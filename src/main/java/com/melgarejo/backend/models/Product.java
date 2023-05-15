package com.melgarejo.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message="El nombre del producto no puede ir vacio")
    @Size(min = 4, max = 12 , message = "El tamaño del nombre del producto debe estar entre 4 y 12 caracteres")
    @Column(nullable = false)
    private String nombre;


    @Column(nullable = false)
    private Integer price;



    private Integer account;



    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;
    private static final long serialVersionUID= 1L;
}
