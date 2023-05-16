package com.melgarejo.backend.services;


import com.melgarejo.backend.dao.ICategoryDao;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IProductServiceImpl implements  IProductService {

    @Autowired
    private IProductDao productDao;


    @Autowired
    private ICategoryDao categoryDao;

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
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchById(Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        try{
            Optional<Product> product = productDao.findById(id);
            if(product.isPresent()){
                System.out.println("encontrada");
                list.add(product.get());
                response.getProductResponse().setProduct(list);
                response.setMetadata("Respuesta Ok","00","Producto encontrado");
            }else{
                System.out.println("no encontrada");
                response.setMetadata("Respuesta nok","-1","Producto no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e) {
            response.setMetadata("Respuesta nok", "-1", "Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ProductResponseRest> save(Product product) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try{
            Optional<Category> categoryDb = categoryDao.findById(product.getCategory().getId());
            if(categoryDb.isPresent()){
                product.setCategory(categoryDb.get());
            }else{
                response.setMetadata("Respuesta nok","-1","Categoria no encontrada asociada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            Product productSaved = productDao.save(product);

            if(productSaved != null){
                list.add(productSaved);
                response.getProductResponse().setProduct(list);
                response.setMetadata("Respuesta Ok", "00","Producto guardado con exito!");
            }else{
                response.setMetadata("Respuesta nok", "-1","Producto no guardado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (DataAccessException e) {
            response.setMetadata("Respuesta nok", "-1", "Error al guardar producto");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductResponseRest> update(Product product, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponseRest> delete(Long id) {
         ProductResponseRest response = new ProductResponseRest();
         List<Product> list = new ArrayList<>();

         try{
             Optional<Product> productDb = productDao.findById(id);
             if(productDb.isPresent()){
                 list.add(productDb.get());
                 productDao.deleteById(id);
                 response.getProductResponse().setProduct(list);
                 response.setMetadata("Respuesta Ok","00","Producto Eliminado con exito!");
             }else{
                 response.setMetadata("Respuesta nok","1", "Producto no encontrado!");
                 return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
             }
         }catch (DataAccessException e) {
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar producto");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

         return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }
}
