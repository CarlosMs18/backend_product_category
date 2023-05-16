package com.melgarejo.backend.services;


import com.melgarejo.backend.dao.IProductDao;
import com.melgarejo.backend.models.Category;
import com.melgarejo.backend.models.Product;
import com.melgarejo.backend.response.CategoryResponseRest;
import com.melgarejo.backend.response.ProductResponse;
import com.melgarejo.backend.response.ProductResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IProductServiceImpl implements  IProductService {

    @Autowired
    private IProductDao productDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> search() {
        ProductResponseRest response = new ProductResponseRest();
        try {
            List<Product> products = (List<Product>) productDao.findAll();
            response.getProductResponse().setProduct(products);
            response.setMetadata("Respuesta Ok","00","Respuesta exitoso");

        }catch (DataAccessException e){
            response.setMetadata("Respuesta nok","-1","Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponseRest> searchById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponseRest> save(Product product) {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponseRest> update(Product product, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponseRest> delete(Long id) {
        return null;
    }
}
