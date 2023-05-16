package com.melgarejo.backend.services;

import com.melgarejo.backend.dao.ICategoryDao;
import com.melgarejo.backend.models.Category;
import com.melgarejo.backend.response.CategoryResponse;
import com.melgarejo.backend.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Service
public class ICategoryServiceImpl implements  ICategoryService{

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();
        try {
            List<Category> category = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategory(category);
            response.setMetadata("Respuesta OK","00", "Respuesta Exitosa");
            System.out.println("ok1");
        }catch (DataAccessException e){
            response.setMetadata("Respuesta nok","-1","Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("ok2");
        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try{
            Category categorySaved = categoryDao.save(category);
            if(categorySaved != null){
                list.add(categorySaved);
                response.getCategoryResponse().setCategory(list);

                response.setMetadata("Respuesta ok","00","Categoria Guardada");
            }else{
                response.setMetadata("Respuesta nok","-1","Categoria no guardada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (DataAccessException e){
            response.setMetadata("Respuesta nok","-1","Error al grabar la categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        return null;
    }
}
