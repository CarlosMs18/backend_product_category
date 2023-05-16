package com.melgarejo.backend.controllers;


import com.melgarejo.backend.models.Category;
import com.melgarejo.backend.response.CategoryResponseRest;
import com.melgarejo.backend.services.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> crearCategory(@RequestBody Category category){
        ResponseEntity<CategoryResponseRest> response =categoryService.save(category);
        return response;
    }

}
