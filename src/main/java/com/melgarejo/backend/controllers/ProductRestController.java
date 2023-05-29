package com.melgarejo.backend.controllers;


import com.melgarejo.backend.models.Category;
import com.melgarejo.backend.models.Product;
import com.melgarejo.backend.response.ProductResponseRest;
import com.melgarejo.backend.services.IProductService;
import com.melgarejo.backend.util.Util;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
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


    @GetMapping("/products/filter/{name}")
    public ResponseEntity<ProductResponseRest> searchProductName(@PathVariable String name){
        ResponseEntity<ProductResponseRest> response = service.searchProductByName(name);
        return response;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> searchById(@PathVariable  Long id){
        ResponseEntity<ProductResponseRest> response = service.searchById(id);
        return response;
    }


    @PostMapping("/products")
    public ResponseEntity<ProductResponseRest> save(
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("nombre") String nombre,
            @RequestParam("price") int price,
            @RequestParam("account") int account,
            @RequestParam("categoryId") Long categoryID

    ) throws IOException {
        Product product = new Product();
        product.setNombre(nombre);
        product.setAccount(account);
        product.setPrice(price);
        product.setPicture(Util.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = service.save(product,categoryID);

        return response;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> update(
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("nombre") String nombre,
            @RequestParam("price") int price,
            @RequestParam("account") int account,
            @RequestParam("categoryId") Long categoryID,
            @PathVariable Long id

    ) throws IOException {
        Product product = new Product();
        product.setNombre(nombre);
        product.setAccount(account);
        product.setPrice(price);
        product.setPicture(Util.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = service.update(product, categoryID, id);

        return response;
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
       ResponseEntity<ProductResponseRest> response = service.delete(id);
       return response;
    }

}
