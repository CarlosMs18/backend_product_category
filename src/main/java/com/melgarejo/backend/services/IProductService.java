package com.melgarejo.backend.services;

import com.melgarejo.backend.models.Category;
import com.melgarejo.backend.models.Product;
import com.melgarejo.backend.response.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    public ResponseEntity<ProductResponseRest> search();

    public ResponseEntity<ProductResponseRest> searchProductByName(String name);


    public ResponseEntity<ProductResponseRest> searchById(Long id);

    //public ResponseEntity<ProductResponseRest> save(Product product);

    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);

    public ResponseEntity<ProductResponseRest> update(Product product , Long categoryId, Long id);

    public ResponseEntity<ProductResponseRest> delete(Long id);
}
