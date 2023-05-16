package com.melgarejo.backend.services;

import com.melgarejo.backend.models.Category;
import com.melgarejo.backend.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    public ResponseEntity<CategoryResponseRest> search();

    public ResponseEntity<CategoryResponseRest> searchById(Long id);

    public ResponseEntity<CategoryResponseRest> save(Category category);

    public ResponseEntity<CategoryResponseRest> update(Category category, Long id);

    public ResponseEntity<CategoryResponseRest> deleteById(Long id);
}
