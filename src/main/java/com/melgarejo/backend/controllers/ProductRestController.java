package com.melgarejo.backend.controllers;


import com.melgarejo.backend.models.Product;
import com.melgarejo.backend.response.ProductResponseRest;
import com.melgarejo.backend.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @GetMapping("/products/{id}")
    public ResponseEntity<?> searchById(@PathVariable  Long id){
        ResponseEntity<ProductResponseRest> response = service.searchById(id);
        return response;
    }


    @PostMapping("/products")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Product product, BindingResult result){
        Map<String, Object> errors = new HashMap<>();
        if(result.hasErrors()) {

            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            errors.put("errors", errores);
            return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<ProductResponseRest> response = service.save(product);
        return response;
    }

}
