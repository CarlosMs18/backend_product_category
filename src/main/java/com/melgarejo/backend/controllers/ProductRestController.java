package com.melgarejo.backend.controllers;


import com.melgarejo.backend.response.ProductResponseRest;
import com.melgarejo.backend.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {


    @Autowired
    private IProductService service;

    @GetMapping("/products")
    public ResponseEntity<?> searchProducts(){
            ResponseEntity<ProductResponseRest> response = service.search();
            return response;
    }
}
