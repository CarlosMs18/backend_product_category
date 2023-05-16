package com.melgarejo.backend.controllers;


import com.melgarejo.backend.models.Category;
import com.melgarejo.backend.response.CategoryResponseRest;
import com.melgarejo.backend.services.ICategoryService;
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
public class CategoryRestController {


    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories(){
            ResponseEntity<CategoryResponseRest> response = categoryService.search();
            return response;
    }


    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoryPorId(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = categoryService.searchById(id);
        return response;
    }

    @PostMapping("/categories")
    public ResponseEntity<?> crearCategory(@Valid @RequestBody Category category, BindingResult result){
        Map<String, Object> errors = new HashMap<>();
        if(result.hasErrors()) {

            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            errors.put("errors", errores);
            return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<CategoryResponseRest> response =categoryService.save(category);
        return response;
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updatedCategory(@Valid @RequestBody Category category , BindingResult result , @PathVariable Long id){
        Map<String, Object> errors = new HashMap<>();
        if(result.hasErrors()) {

            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            errors.put("errors", errores);
            return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<CategoryResponseRest> response =categoryService.update(category , id);
        return response;
    }




    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = categoryService.deleteById(id);
        return response;
    }


}
