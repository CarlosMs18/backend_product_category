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
import java.util.Optional;

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
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list =new ArrayList<>();
        try {
            Optional<Category> category =  categoryDao.findById(id);
            if(category.isPresent()){
                list.add(category.get());
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Respuesta Ok","00","Categoria Encontrada");
            }else{
                response.setMetadata("Respuesta nok","-1","Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e ){
            response.setMetadata("Respuesta nok","-1","Error al buscar la categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
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
    @Transactional
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try{
            Optional<Category> categoryDB =  categoryDao.findById(id);
            if(categoryDB.isPresent()){

                categoryDB.get().setNombre(category.getNombre());
                categoryDB.get().setDescripcion(category.getDescripcion());

                Category categoryUpdated = categoryDao.save(categoryDB.get());

                if(categoryUpdated != null){
                    list.add(categoryDB.get());
                    response.getCategoryResponse().setCategory(list);
                    response.setMetadata("Respuesta Ok","00","Categoria actualizada");
                }else{
                    response.setMetadata("Respuesta nok","-1","Categoria no actualizada");
                    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
                }


            }else{
                response.setMetadata("Respuesta nok","-1","Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.setMetadata("Respuesta nok","-1","Error al actualizar la categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try{
            Optional<Category> category =  categoryDao.findById(id);
            if(category.isPresent()){
                list.add(category.get());
                categoryDao.deleteById(id);
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Respuesta Ok","00","Categoria Eliminada");
            }else{
                response.setMetadata("Respuesta nok","-1","Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e){
            response.setMetadata("Respuesta nok","-1","Error al eliminar la categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
